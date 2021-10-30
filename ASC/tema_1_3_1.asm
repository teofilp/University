bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 10
    b db 15
    c db 20
    d dw 100
    result resw 2 ; final result

; our code starts here
segment code use32 class=code
; 300-[5*(d-2*a)-1]
    start:
        
        mov al, [a] ; al <- a
        mov cl, byte 2 ; cl <- 2
        mul cl ; ax <- al * cl <=> ax <- a*2
        
        mov bx, [d] ; bx <- d
        
        sub bx, ax ; bx <- bx - ax <=> bx <- d - 2*a
        
        mov ax, bx ; ax <- bx <=> ax <- d - 2*a
        mov bx, word 5 ; bx <- 5
        
        mul bx ; dx:ax <- ax * bx <=> eax <- 5*(d - 2*a)
        
        push dx
        push ax
        pop eax
        
        sub eax, dword 1 ; eax <- eax - 1 <=> eax <- 5*(d - 2*a) - 1
        neg eax ; eax <- -eax <=> eax <- -(5*(d - 2*a) - 1)
        
        add eax, dword 300 ; eax <- eax + 300 <=> eax <- 300 - (5*(d - 2*a) - 1)
        mov [result], eax

        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
