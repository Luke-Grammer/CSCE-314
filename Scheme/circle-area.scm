
(define (square x)
  (* x x)
)

(define (circle_area r)
  (* 3.14159 (square r))
)

(define (main)
  (display "Enter a radius: ")
  (display (circle_area (read)))
  (display " is the area.")
  (newline)
  (exit)
)

(main)
