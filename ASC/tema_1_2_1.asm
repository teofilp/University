bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 5
    b db 2
    c db 3
    d db 1
    res resb 1 ; final result

; our code starts here
segment code use32 class=code
; (a+a-c) - (b+b+b+d)
    start:
        mov al, [a] ; al <- a
        add al, [a] ; al <- al + a <=> al <= a + a
        sub al, [c] ; al <- al - c <=> al <= a + a - c
        
        mov bl, [b] ; bl <- b
        add bl, [b] ; bl <- bl + b <=> bl <- b + b
        add bl, [b] ; bl <- bl + b <=> bl <- b + b + b
        add bl, [d] ; bl <- bl + d <=> bl <- b + b + b + d
        
        sub al, bl
        mov [res], al
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
