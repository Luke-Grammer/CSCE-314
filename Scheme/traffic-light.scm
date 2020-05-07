
(define (trafficLight color)
  (cond
    ((equal? color "green") (display "It's Green\n"))
    ((equal? color "yellow") (display "It's Yellow\n"))
    ((equal? color "red") (display "stop\n"))
    (else (display "wrong color\n"))
    )
  )

(trafficLight "blue")
(trafficLight "green")