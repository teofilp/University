(defun factorial (n)
    (cond 
        ((zerop n) 1)
        (t (* n (factorial (- n 1))))
    )
)

; ins (l1l2..ln, el, c):
;    - [], n = 0
;    - l1 U el U ins(l2..ln, el, c+1), c % 2 = 0
;    - l1 U ins(l2...ln, el, c+1), otherwise
; a
; (ins_main (list 1 2 3 4 5 6 7 8) 5)

(defun ins (l el c)
    (cond
        ((null l) nil)
        ((= (mod c 2) 0) (append (list (car l) el) (ins2 (cdr l) el (+ c 1)))) 
        (t (append (list (car l)) (ins2 (cdr l) el (+ c 1)))) 
    )
)

(defun ins_main (l el)
    (ins l el 1)
)

; get_atoms(l1l2...ln)
;   - [], n = 0
;   - l1 U get_atoms(l2...ln), l1 is an atom
;   - get_atoms(l1) U get_atoms(l2...ln), l1 is a list

; b
; (get_atoms_main `(((A B) C) (D E)))

(defun get_atoms (l)
    (cond
        ((null l) nil)
        ((atom (car l)) (cons (car l) (get_atoms (cdr l))))
        ((listp (car l)) (append (get_atoms (car l)) (get_atoms (cdr l))))
    )
)
; get_atoms_main(l1l2..ln)
;   - reverse(get_atoms(l1l2...ln))
(defun get_atoms_main (l)
    (cond
        (t (reverse (get_atoms l)))
    )
)

; c
; my_gcd(a b)
;   a, b = 0
;   my_gcd(b, a mod b), otherwise
(defun my_gcd (a b)
    (cond 
        ((= b 0) a)
        (t (my_gcd b (mod a b)))
    )
)

; list_gcd(l1l2..ln)
;   - list_or_atoms_gcd(l1), n = 1
;   - my_gcd(list_or_atom_gcd(l1), list_gcd(l2...ln)), otherwise

; (list_gcd (list 12 6 (list 3)))
; (list_gcd (list `A 4 (list 2)))
; (list_gcd (list `A 4 (list 2) (list 6 8 (list 2)))) ; (A 4 (2) (6 8 (2))))
(defun list_gcd(l)
    (cond
        ((= (length l) 1) (list_or_atom_gcd (car l)))
        (t (my_gcd (list_or_atom_gcd (car l)) (list_gcd (cdr l))))
    )
)

; list_or_atom_gcd(l)
;   - l, if l is a number
;   - list_gcd(l), l is a number
;   - 0, if l is an atom
(defun list_or_atom_gcd(l)
    (cond
        ((numberp l) l)
        ((listp l) (list_gcd l))
        ((atom l) 0)
    )
)

;d
; occurences(l1l2..ln, el, oc)
;   - oc, n = 0
;   - occurences(l1, el, 0) + occurences(l2...ln, el, 0), l1 is a list
;   - 1 + occurences(l2..ln, el, 0), l1 is an atom and l1 = el
;   - occurences(l2...ln, el, 0), otherwise

; (occurences (list 1 2 1 (list 1 `A `A (list `A `B))) `A 0)
(defun occurences(l el oc)
    (cond
        ((null l) oc)
        ((listp (car l)) (+ (occurences (car l) el 0) (occurences (cdr l) el 0)))
        ((and (atom (car l)) (equal (car l) el)) (+ 1 (occurences (cdr l) el 0)))
        (t (occurences (cdr l) el 0))
    )
)

; (occurences_main (list 1 2 (list 1 (list 1))) 1)
; (occurences_main (list 1 2 1 (list 1 `A `A (list `A `B))) `A)
(defun occurences_main(l el)
    (occurences l el 0)
)