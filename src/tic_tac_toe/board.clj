(ns tic-tac-toe.board
  ":x represents X, :o represents O and nil represents empty.
  With this we can determine winner by checking if all symbols are equals
  in a row, column or diagonal except nils.")

(defn board
  "Returns a nxn matrix filled with nil's"
  [n]
  (->> (repeat n nil)
       (into [])
       (repeat n)
       (into [])))

(def default-board (board 3))

(defn transpose
  "Returns transpose of the board. Rows become columns and columns becomes rows"
  [board]
  (apply mapv vector board))

(defn get-cell
  "Get the value from given row and column of the board"
  [board r c]
  (-> board
      (nth r)
      (nth c)))

(defn diagonals
  "Get diagonal items from board"
  [board]
  (map (fn [i] (get-cell board i i))
       (range (count board))))

(defn anti-diagonals
  "Get anti-diagonal items from board"
  [board]
  (let [n (count board)]
    (map (fn [i] (get-cell board (- n i 1) i))
         (range n))))

(defn find-winner
  [row]
  (->> row
       (partition-by identity)
       (map (juxt first count))
       (filter (fn [[_ c]] (>= c 3)))
       ffirst))

(defn row-winner
  "Returns the row wise winner of a board, else nil"
  [board]
  (reduce (fn [_ row]
            ;; when all values are same in row and not nil
            (when-let [result (find-winner row)]
              (reduced result)))
          []
          board))

(defn column-winner
  "Returns the column wise winner of the board, else nil"
  [board]
  (row-winner (transpose board)))

(defn diagonal-winner
  "Returns the diagonal wise winner of the board, else nil"
  [board]
  (let [items (diagonals board)]
    (find-winner items)))

(defn anti-diagonal-winner
  "Returns the anti-diagonal wise winner of the board, else nil"
  [board]
  (let [items (anti-diagonals board)]
    (find-winner items)))

(defn winner
  "Returns winner if there is a winner else returns :no-winner"
  [board]
  (or
   (row-winner board)
   (column-winner board)
   (diagonal-winner board)
   (anti-diagonal-winner board)
   :no-winner))
