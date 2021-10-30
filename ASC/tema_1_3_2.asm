bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    a db 2
    b db 3
    c db 4
    d db 5
    e dw 10
    f dw 15
    g dw 20
    h dw 25

; our code starts here
segment code use32 class=code
    start:
; h/a + (2 + b) + f/d – g/c
        
        mov ax, [h] ; ax <- h
        div byte [a] ; al <- ax / a <=> al <- h/a ; ah <- ax % a <=> ah <- h % a
        mov bl, al ; bl <- al <=> bl <- h / a  
        
        mov ax, [f] ; ax <- f
        div byte [d] ; al <- ax / d <=> al <- f / d
        mov cl, al  ; cl <- al <=> cl <- f / d
        
        mov ax, [g] ; ax <- g
        div byte [c] ; al <- ax / c <=> al <- g / c
        mov dl, al ; dl <- al <=> dl <- g / c
        neg dl ; dl <- -dl <=> dl <- -(g/c)
        
        mov bh, [b] ; bh <- b 
        add bh, 2 ; bh <- bh + 2 <=> bh <- b + 2
        
        mov al, bl ; al <- bl <=> al <- h/a
        add al, cl ; al <- al + cl <=> al <- h/a + f/d
        add al, dl ; al <- al + dl <=> al <- h/a + f/d - g/c
        add al, bh ; al <- al + bh <=> al <- h/a + f/d - g/c + b + 2
        ; al - final result
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
