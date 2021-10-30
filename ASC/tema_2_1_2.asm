bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    a db 10
    b dw 20
    c dd 50
    d dq 100
    r1 resq 1
    

; our code starts here
segment code use32 class=code
; (c+d-a)-(d-c)-b
    start:
        
        mov ecx, [d] ; 
        mov ebx, [d+4] ; ebx:ecx <- d
        
        mov eax, [c]
        cdq ; edx:eax <- c
        
        sub ecx, eax
        sbb ebx, edx ; ebx:ecx <- d - c
        
        mov [r1], ecx
        mov [r1+4], ebx ; r1 <- d - c
        
        mov al, [a]
        cbw
        cwde ; eax <- a
        
        neg eax ; eax <- -eax = -a
        add eax, [c]; eax <- c - a
        
        cdq ; edx:eax <- c - a
        
        mov ecx, [d]
        mov ebx, [d+4] ; ebx:ecx <- d
        
        add eax, ecx
        adc edx, ebx ; edx:eax <- c - a + d
        
        mov ecx, dword [r1]
        mov ebx, dword [r1+4] ; ebx:ecx <- r1 = d - c
        
        sub eax, ecx
        sbb edx, ebx ; <- edx:eax <- (c-a+d)-(d-c)
        
        mov ebx, dword 0
        mov ecx, dword 0 
        mov cx, [b]; ebx:ecx <- b
        
        sub eax, ecx
        sbb edx, ebx ; edx:eax <- final result
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
