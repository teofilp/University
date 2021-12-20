(defun factorial (n)
    (cond 
        ((zerop n) 1)
        (t (* n (factorial (- n 1))))
    )
)

; a
; (ins (list 1 2 3 4 5 6 7 8) 5)
(defun ins (l el)
    (cond
        ((null l) nil)
        (t (append (list (car l) (cadr l) el) (ins (cddr l) el)))
    )
)

; b
; (get_atoms_main (list (list (list `A `B) `C) (list `D `E)))
(defun get_atoms (l)
    (cond
        ((null l) nil)
        ((atom (car l)) (cons (car l) (get_atoms (cdr l))))
        ((listp (car l)) (append (get_atoms (car l)) (get_atoms (cdr l))))
    )
)

(defun get_atoms_main (l)
    (cond
        (t (reverse (get_atoms l)))
    )
)

; c
(defun my_gcd (a b)
    (cond 
        ((= b 0)a)
        (t (my_gcd b (mod a b)))
    )
)

(defun search_gcd (l)
    (cond 
        ((= (length l) 1) (car l))
        ((= (length l) 2) (my_gcd (car l) (cadr l)))
        ((> (length l) 2) (my_gcd (my_gcd (car l) (cadr l)) (search_gcd (cddr l)))))
    )
)