(load "sort.scm")

(define (main)
  (define list1 '("Test" "This" "List"))

  (display (sort list1 string-ci<?))
  (newline)
  (exit)
)

(main)
