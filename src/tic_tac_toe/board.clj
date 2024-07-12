(ns tic-tac-toe.board
  "0 represents player1, 1 represents player2 and nil represents empty")

(def default-board
  "Returns a 3x3 matrix filled with nils"
  (for [x (range 3)]
    (for [y (range 3) ]
      nil)))

