(define (list-search arr val)
  (cond
    ((null? arr) #f)
    ((equal? (car arr) val) #t)
    ((list? (car arr)) (list-search (car arr) val))
    (else (list-search (cdr arr) val))
  )
)

(define (deep-search arr val)
  (cond
    ((null? arr) #f)
    ((list-search arr val) #t)
    (else (deep-search (cdr arr) val))
  )
)

(define (main)
  (define Z '(2 (0 9) 34 B ("D" (6))))
  
  (display (deep-search Z 6))
  (newline)
)

(main)
