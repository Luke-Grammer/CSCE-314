(define (list-sum x)
  (if (null? x)
    0
    (+ (car x) (list-sum (cdr x)))
  )
)


(define (main)
  (define x '(2 3 5 7))
  (display (list-sum x))
  (newline)
  (exit)
  )

(main)
