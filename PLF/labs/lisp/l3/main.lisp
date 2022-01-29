; my_reverse(el)
;   - [], el is l1l2..ln and n = 0
;   - my_reverse(foreach l in reverse(el)), el is a list
;   - el, if el is a number

(defun my_reverse(el)
    (cond 
        ((null el) nil)
        ((listp el) (mapcar #'my_reverse (reverse el)))
        ((numberp el) el)
    )
)
;(my_reverse `(1 2 3 (1 2 (1 2 3))))


