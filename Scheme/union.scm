(define (list-search arr val)
  (if (null? arr) #t
    (if (equal? (car arr) val) #f
	(list-search (cdr arr) val)
    )
  )
)

(define (union list1 list2)
  (cond
   ((null? list2) list1)
   ((list-search list1 (car list2)) (cons (car list2) (union list1 (cdr list2))))
   (else (union list1 (cdr list2)))
  )
)

(define (main)
  (define Z '(5 8 9))
  (define A '(A B C))
  
  (display (union Z A))
  (newline)
)

(main)
