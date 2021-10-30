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
    c db 20
    b dd 30
    x dq 50
    r1 resd 1

; our code starts here
segment code use32 class=code
; x-(a*a+b)/(a+c/a)
    
    start:
        
            
        mov al, [a] 
        imul al ; ax <- a*a
        cwde ; eax <- a*a
        
        add eax, [b] ; eax <- eax + b = a*a+b
        mov [r1], eax ; r1 <- a*a+b
        
        mov al, [c] 
        cbw ; ax <- c
        idiv byte [a] ; al <- c/a ; ah <- c%a
        add al,[a]
        
        cbw ; ax <- a + c/a
        
        mov bx, ax ; bx <- ax = a + c/a
        
        mov ax, [r1]
        mov dx, [r1+2]
        idiv bx ; dx <- (a*a+b)%(a+c/a); ax <- (a*a+b)/(a+c/a)
        
        
        
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
