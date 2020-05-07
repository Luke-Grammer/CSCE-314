(define (replicate lst n)
  (cond
    ((equal? 0 n) lst)
    ((list? lst) (rep-helper lst lst n))
  )
)

(define (rep-helper orig-lst lst n)
  (cond
    ((equal? 1 n) lst)
    ((list? lst) (rep-helper orig-lst (append lst orig-lst) (- n 1)))
  )
)

(replicate '(1 2 3) 2)


