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
    b dw 400
    c dd 500
    d dq 100
    f1 resd 1
    f2 resq 1
; our code starts here
segment code use32 class=code
; (c+c-a)-(d+d)-b
    start:
        ; ...
        mov eax, dword 0 ; eax <- 0
        mov eax, [c] ; eax <- c
        add eax, [c] ; eax <- c + c
        mov ebx, dword 0 ; ebx <- 0
        mov bl, [a] ; bl <- a
        sub eax, ebx ; eax <- eax - ebx <=> eax <- c + c - a
        mov [f1], eax ; f1 <- c + c - a
        
        mov eax, [d]
        mov edx, [d+4] ; edx:eax <- d
        
        mov ebx, [d]
        mov ecx, [d+4] ; ecx:ebx <- d
        
        add eax, ebx
        adc edx, ecx ; edx:eax <- edx:eax + ecx:ebx <=> d+d
        
        mov ecx, dword 0
        mov ebx, dword[f1]
        
        sub ebx, eax
        sbb ecx, edx ; ecx:ebx <- (c+c-a)-(d+d)
        
        mov eax, dword 0
        mov ax, [b]
        mov edx, dword 0 ; edx:eax <- b
        
        sub ebx, eax
        sbb ecx, edx ; ecx:ebx - final result
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
