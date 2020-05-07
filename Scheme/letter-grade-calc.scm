(define (letter-grade x)
  (cond
   ((>= x 90) "A")
   ((>= x 80) "B")
   ((>= x 70) "C")
   ((>= x 60) "D")
   ("F")
  )

)


(define (main)
  (display (letter-grade 99))
  (newline)
  (display (letter-grade 85))
  (newline)
  (display (letter-grade 64))
  (newline)

  (exit)
)

(main)
