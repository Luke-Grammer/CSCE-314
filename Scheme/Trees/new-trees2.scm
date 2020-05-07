#lang racket

(define (leaf? node)
  (null? (children node)))

(define (datum node)
  (car node))

(define (children node)
  (cdr node))

(define (count-leaves tree)
  (if (leaf? tree)
      1
      (count-leaves-in-forest (children tree))
  )
)

(define (count-leaves-in-forest forest)
  (if (null? forest)
      0
      (+ (count-leaves (car forest))
         (count-leaves-in-forest (cdr forest)))
  )
)

(define (make-node datum children)
  (cons datum children))

(define (leaf datum)
  (make-node datum '())
)

(define (cities name-list)
  (map leaf name-list)
)

(define (in-tree? place tree)
  (or (equal? place (datum tree))
      (in-forest? place (children tree))
  )
)

(define (in-forest? place forest)
  (if (null? forest)
      #f
      (or (in-tree? place (car forest))
	    (in-forest? place (cdr forest)))
  )
)

(define (locate city tree)
  (if (equal? city (datum tree))
      (list city)
      (let ((subpath (locate-in-forest city (children tree))))
        (if subpath
            (cons (datum tree) subpath)
            #f))))

(define (locate-in-forest city forest)
  (if (null? forest)
      #f
      (or (locate city (car forest))
	  (locate-in-forest city (cdr forest)))))

(define (left-most node) ;; Finds leftmost leaf in a tree
  (cond
    ((leaf? node) (datum node)) ;; If we've found a leaf, return it's datum
    (else (left-most (cadr node))) ;; Otherwise, recurse with first child
  )
)

(define (end list) ;; Returns last child of a node
  (cond
    ((leaf? list) (datum list)) ;; If we've found a leaf, return it's datum
    (else (end (children list))) ;; Otherwise, recurse with next element
  )
)

(define (right-most node) ;; Finds rightmost leaf in a tree
  (cond
    ((leaf? node) (end node)) ;; If we've found a leaf, return the last atom
    (else (right-most (end node))) ;; Otherwise, recurse with last child
  )
)

(define (depth node) ;; Finds depth of tree
  (cond
    ((leaf? node) 1) ;; If we've reached a leaf, add 1 to depth and unwind recursion
    (else (+ 1 (apply max (map depth (children node))))) ;; Otherwise apply depth to each child recursively, and return the maximum depth found over all children + 1
  )
)

(define (count-nodes node) ;; Find total number of nodes in a tree
  (cond
    ((leaf? node) 1) ;; If we've reached a leaf, return 1 and unwind recursion
    (else (apply + 1 (map count-nodes (children node)))) ;; Otherwise apply count-nodes to each child recursively, and return the sum of all of them + 1
  )
)

(define world-tree2
  (make-node
    'world
    (list
      (make-node 'italy
        (cities '(venezia riomaggiore firenze roma)))

      (make-node '(united states)
        (list
          (make-node 'california
            (cities '(berkeley (san francisco) gilroy)))
          (make-node 'massachusetts
            (cities '(cambridge amherst sudbury)))
          (make-node 'ohio
            (cities '(kent)))))
      
      (make-node 'zimbabwe (cities '(harare hwange)))
      
      (make-node 'china (cities '(beijing shanghai guangzhou suzhou)))
      
      (make-node '(great britain)
        (list 
          (make-node 'england (cities '(liverpool)))
          (make-node 'scotland (cities '(edinburgh glasgow (gretna green))))
          (make-node 'wales (cities '(abergavenny)))
        )
      )
      
      (make-node 'australia
        (list
          (make-node 'victoria (cities '(melbourne)))
          (make-node '(new south wales) (cities '(sydney)))
          (make-node 'queensland (cities '(cairns (port douglas))))
        )
      )
      
      (make-node 'honduras (cities '(tegucigalpa)))
    )
  )
)

; (count-leaves world-tree)
(display (count-leaves world-tree2))

;> (in-tree? 'abergavenny world-tree)
(display (in-tree? 'abergavenny world-tree2))
;#T

;> (in-tree? 'abbenay world-tree)
(display (in-tree? 'abbenay world-tree2))
;#F

;> (in-tree? 'venezia (cadr (children world-tree)))
(display (in-tree? 'venezia (cadr (children world-tree2))))
;#F

(newline)
;> (left-most world-tree2) 
(display (left-most world-tree2))

(newline)
;> (right-most world-tree2)
(display (right-most world-tree2))

(newline)
;> (locate 'berkeley world-tree2)
(display (locate 'berkeley world-tree2))

(newline)
;> (depth world-tree2)
(display (depth world-tree2))

(newline)
;> (count-nodes world-tree2)
(display (count-nodes world-tree2))