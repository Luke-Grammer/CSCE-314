#lang racket

(define (children node)
  (cdr node))

(define (datum node)
  (car node))

(define (leaf? node)
  (null? (children node)))

(define (count-leaves tree)
  (if (leaf? tree)
      1
      (apply + (map count-leaves (children tree)))))

(define (make-node datum children)
  (cons datum children))

(define (leaf datum)
  (make-node datum '()))

(define (cities list)
  (map leaf list))

(define (evaluate tree)
  (if (leaf? tree)
      (datum tree)
      (apply (datum tree) (map evaluate (children tree)))
  ))

(define (end list)
  (if (null? (cdr list))
      (datum list)
      (end (cdr list))
  ))

(define (right-most node)
  (if (leaf? node)
    (end node)
    (right-most (end node))
  ))

(define (left-most node)
  (if (leaf? node)
      (datum node)
      (left-most (datum (children node)))
  ))

(define (depth tree)
  (if (leaf? tree)
      1
      (+ 1 (apply max (map depth (children tree))))
  ))

(define (count-nodes tree)
  (if (leaf? tree)
      1
      (+ 1 (apply + (map count-nodes (children tree))))
  ))

(define parse-tree
  (make-node *
    (list
      (make-node +
        (list
        (leaf 5)
        (leaf 3)))
      (make-node -
        (list
        (leaf 5)
        (make-node *
          (list
            (leaf 2)
            (leaf 2)
         ))))))
)

(display "Evaluation: ")
(display (evaluate parse-tree))
(newline)

(display "Right-most leaf: ")
(display (right-most parse-tree))
(newline)

(display "Left-most leaf: ")
(display (left-most parse-tree))
(newline)

(display "Max depth: ")
(display (depth parse-tree))
(newline)

(display "Node count: ")
(display (count-nodes parse-tree))