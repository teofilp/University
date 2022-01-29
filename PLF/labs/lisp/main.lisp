; 69 D
(defun f(arb level e)
    (cond 
        ((null arb) nil)
        ((and (atom arb) (equal arb e) (equal (mod level 2) 0)) t)
        ((listp arb) (some #'identity (mapcar #'(lambda (arb) (f arb (+ 1 level) e)) arb)))
    )
)

(defun f_main(arb e)
    (f arb -1 e)
)