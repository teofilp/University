(defun traverse_left(tree vertices edges)
    (cond 
        ((null tree) nil)
        ((= vertices (+ 1 edges)) nil)
        (t (cons (car tree)
            (cons (cadr tree)
                (traverse_left (cddr tree) (+ 1 vertices) (+ (cadr tree) edges))
            ))
        )
    )
)

(defun left_subtree(tree)
    (traverse_left (cddr tree) 0 0)
)

(defun traverse_right(tree vertices edges)
    (cond 
        ((null tree) nil)
        ((= vertices (+ 1 edges)) tree)
        (t (traverse_right (cddr tree) (+ 1 vertices) (+ (cadr tree) edges)))
    )
)

(defun right_subtree(tree)
    (traverse_right (cddr tree) 0 0)
)

(defun in_tree (el tree)
    (cond 
        ((null tree) nil)
        ((equal el (car tree)) t)
        (t (in_tree el (cddr tree)))
    )
)

(defun path(el tree result)
    (cond
        ((null tree) nil)
        ((equal (car tree) el) (append result (list el)))
        ((in_tree el (left_subtree tree)) (append result (append (list (car tree)) (path el (left_subtree tree) result))))
        ((in_tree el (right_subtree tree)) (append result (append (list (car tree)) (path el (right_subtree tree) result))))
        (t nil)
    )
)

; (path_main `D `(A 2 B 0 C 2 D 0 E 0))
(defun path_main (el tree)
    (path el tree (list ))
)