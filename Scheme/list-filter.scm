(define (collect-types list out t)
  (cond
    ((null? list) out) 
    ((t (car list)) (collect-types (cdr list) (cons (car list) out) t) )
    (else
     (collect-types (cdr list) out t)
    )
  )
)

(define (main)
  (define Z '(6 3 B 9 4))
  (define out '())
  (display (collect-types Z out number?))
  (newline)
)

(main)
