(define (fib n)
  (if (equal? n 0)
    0
    (if (< n 3)
      1
      (+ (fib(- n 1)) (fib(- n 2)))
    )
  )
)

(define (main)
  (define n 0)
  (display "Enter a number for fib: ")
  (set! n (read))
  (display "\nN'th fibbonacci number: ")
  (display (fib n))
  (display "\n\n")
  (exit)
)

(main)
