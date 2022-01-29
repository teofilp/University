; traverse_left (l1l2n...ln, vertices, edges)
;   - false, n = 0
;   - false, vertices = edges + 1
;   - l1 U l2 U traverse_left(l3...ln, vertices + 1, edges + l2)

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

; traverse_right (l1l2n...ln, vertices, edges)
;   - false, n = 0
;   - l1l2..ln, vertices = edges + 1
;   - traverse_left(l3...ln, vertices + 1, edges + l2)
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

; in_tree(el, l1l2...ln)
;   - false, n = 0
;   - true, el = l1
;   - in_tree(el, l3...ln), otherwise
(defun in_tree (el tree)
    (cond 
        ((null tree) nil)
        ((equal el (car tree)) t)
        (t (in_tree el (cddr tree)))
    )
)


; path(el, l1l2...ln, k1k2...kn)
;   - [], n = 0
;   - k1k2...kn U el, l1 = el
;   - k1k2...kn U el U path(el, left_subtree(tree), result), in_tree(el, left_subtree(tree))
;   - k1k2...kn U el U path(el, right_subtree(tree), result), in_tree(el, right_subtree(tree))
;   - [], otherwise
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