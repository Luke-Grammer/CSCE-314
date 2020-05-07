
(define (min-list-val list curr-min)
  (cond
    ((null? list) (append '() curr-min))
    ((< (car list) curr-min) (min-list-val (cdr list) (car list)))
    (else (min-list-val (cdr list) curr-min))
  )
)

(define (max-list-val list curr-max)
  (cond
    ((null? list) (append '() curr-max))
    ((> (car list) curr-max) (max-list-val (cdr list) (car list)))
    (else (max-list-val (cdr list) curr-max))
  )
)

(define (main)
  (define Z '(1 4 7 2 -4))
  (display (min-list-val Z 100000))
  (newline)
  (display (max-list-val Z -100000))
  (newline)
  
  (exit)
  )

(main)
