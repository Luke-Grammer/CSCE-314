
(define (power x pow)
  (cond
    ((equal? pow 0) 1)
    (else (equal? pow 1) x
      (* x (power x (- pow 1)))
    )
  )
)

(define (main)
  (display (power 2 2))
  (newline)
  (display (power 2 6))
  (newline)
  (display (power 2 0))
  (newline)
  (exit)
)

(main)
