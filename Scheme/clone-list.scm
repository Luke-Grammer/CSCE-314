(define (clone lst n)
  (cond
    ((zero? n) lst)
    ((list? lst) (clone (cons (car lst) lst) (- n 1)))
    (else (clone (cons lst '()) (- n 1)))
  )
)

(display (clone 3 6))