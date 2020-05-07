; Written by: Luke Grammer
; Date: 9/5/18

; 'add' will add two or 3 numbers, using an optional parameter for the third
(define (add x y #!optional (b 0))
  (+ x y b)
)

; calls 'add' with 2, then 3 arguments, and displays result for each
(define (main)
  (display (add 3 7))
  (newline)
  (display (add 3 7 9))
  (newline)
  (exit)
)

(main)
