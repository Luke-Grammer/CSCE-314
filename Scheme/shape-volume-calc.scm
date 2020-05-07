
(define (sphereVol x)
  (/ (* 3.14 x x x) 6)
)

(define (rectVol h w l)
  (* h w l)
)

(define (isContained x y)
  (if
   (<= x y) #t #f
  )
)


(define (main)
  (display (sphereVol 15))
  (newline)
  (display (rectVol 10 8 45))
  (newline)
  (display (isContained (sphereVol 15) (rectVol 10 8 45)))
  (newline)
  (display (sphereVol 15.5))
  (newline)
  (display (rectVol 10 8 48))
  (newline)
  (display (isContained (sphereVol 15.5) (rectVol 10 8 48)))
  (newline)
  (display (sphereVol 16))
  (newline)
  (display (rectVol 10 8 53))
  (newline)
  (display (isContained (sphereVol 16) (rectVol 10 8 53)))
  (newline)
  (exit)
  )

(main)
