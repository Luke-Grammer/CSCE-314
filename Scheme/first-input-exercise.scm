; File: first-input-exercise.scm
; Written by: Luke Grammer
; Date: 8/31/18
; TAMU email: Luke.grammer@tamu.edu
; Class: CSCE 314-501
; Description: This is grabbing values and displaying their name and address


(define name #\ )
(define address #\ )

; 1. ) Create the CODE to LITERALLY display YOUR name, and address (No variables yet. )

(display "My name is Luke Grammer, and my address is 3150 Finfeather Rd.\n")
(display "\nWhat is your information?\n")

; 2. ) Create the CODE to ask for user's name and address, USE THE VARIABLES DECLARED FOR YOU ALREADY!! Hint: Which scanner functions will you need?

(display "\nEnter your name: ")
(set! name (read-line))

(display "\nEnter your address: ")
(set! address (read-line))

; 3. ) Create the CODE to display their name and address that THEY type in. NOT yours!!

(display "Your name is ")
(display name)
(display " and your address is ")
(display address)
(display "\n\n")
