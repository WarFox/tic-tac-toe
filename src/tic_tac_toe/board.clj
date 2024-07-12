(ns tic-tac-toe.board
  "1 represents X, -1 represents O and 0 represents empty.
  With this we can determine winner by summing rows, columns and diagonals.
  X wins when sum of rows, columns or diagonals is 3
  O wins when sum of rows, columns or diagonals is -3")

(defn board
  "Returns a nxn matrix filled with 0's"
  [n]
  (->> (repeat n 0)
       (into [])
       (repeat n)
       (into [])))

(def default-board (board 3))

(defn sum-row
  "Returns sum of rows as list"
  [board]
  (map #(reduce + %) board))

(defn transpose
  "Returns transpose of the board. Rows become columns and columns becomes rows"
  [board]
  (apply mapv vector board))

(defn winner
  "Returns winner if there is a winner else returns nil"
  [board]
  (let [row-sums (sum-row board)
        col-sums (sum-row (transpose board))
        n       (count board)]
    (cond
      ;; check rows
      (some #{n} row-sums)
      1

      (some #{(- n)} row-sums)
      -1

      ;; check colums
      (some #{n} col-sums)
      1

      (some #{(- n)} col-sums)
      -1

      :else nil)))
