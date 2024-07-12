(ns tic-tac-toe.board
  "1 represents X, -1 represents O and 0 represents empty.
  With this we can determine winner by summing rows, columns and diagonals")

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

(defn winner
  "Returns winner if there is a winner else returns nil"
  [board]
  (let [sum-r (sum-row board)
        n     (count board)]
    (cond
      ;; check row
      (some #{n}  sum-r)
      1

      (some #{(- n)} sum-r)
      -1

      :else nil)))
