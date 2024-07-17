(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.board :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest board-test
  (testing "new board"
    (is (= (sut/board 2)
           [[nil nil]
            [nil nil]]))))

(deftest default-board-test
  (testing "default board of size 3x3 nil's"
    (is (= sut/default-board
           [[nil nil nil]
            [nil nil nil]
            [nil nil nil]]))))

(deftest transpose-test
  (testing "transpose board"
    (is (= [[1 4 7]
            [2 5 8]
            [3 6 9]]
           (sut/transpose [[1 2 3]
                           [4 5 6]
                           [7 8 9]])))))

(deftest get-cell
  (testing "can get a cell correctly"
    (let [board [[1 2 3]
                 [4 5 6]
                 [7 8 9]]]
      (is (= 1 (sut/get-cell board 0 0)))
      (is (= 2 (sut/get-cell board 0 1)))
      (is (= 3 (sut/get-cell board 0 2)))

      (is (= 4 (sut/get-cell board 1 0)))
      (is (= 5 (sut/get-cell board 1 1)))
      (is (= 6 (sut/get-cell board 1 2)))

      (is (= 7 (sut/get-cell board 2 0)))
      (is (= 8 (sut/get-cell board 2 1)))
      (is (= 9 (sut/get-cell board 2 2))))))

(deftest diagonals-test
  (testing "returns diagonal items"
    (is (= '(1 2 3)
           (sut/diagonals [[1 0 1]
                           [0 2 0]
                           [1 1 3]])))))

(deftest anti-diagonals-test
  (testing "returns anti diagonal items"
    (is (= '(1 2 3)
           (sut/anti-diagonals [[1 0 3]
                                [0 2 0]
                                [1 1 0]])))))

(deftest row-winner-test
  (testing "returns the row winner of a board, else nil"
    (is (= :o
           (sut/row-winner [[:o :o :o]
                            [:x nil nil]
                            [:o nil :o]])))
    (is (= :o
           (sut/row-winner [[nil :o nil]
                            [:o :o :o]
                            [:o nil :o]])))
    (is (= :o
           (sut/row-winner [[nil :o :o]
                            [:o nil nil]
                            [:o :o :o]])))
    (is (= :x
           (sut/row-winner [[:x :o :o]
                            [:x :x :x]
                            [:o :o :x]])))
    (is (nil?
         (sut/row-winner [[nil :o :o]
                          [nil :o nil]
                          [:o nil :o]]))))

  (testing "full nil rows are ignored"
    (is (= :o
           (sut/row-winner [[nil :o :o]
                            [nil nil nil]
                            [:o :o :o]])))))

(deftest column-winner-test
  (testing "returns the column winner of a board, else nil"
    (is (= :o
           (sut/column-winner [[:o :o nil]
                               [:o :x :o]
                               [:o nil :o]])))
    (is (= :x
           (sut/column-winner [[:x :o :x]
                               [:x :x nil]
                               [:x nil :o]])))
    (is (nil?
         (sut/column-winner [[nil :o :o]
                             [nil :o nil]
                             [:o nil :o]])))))

(deftest diagonal-winner-test
  (testing "returns the diagonal winner of a board, else nil"
    (is (= :o
           (sut/diagonal-winner [[:o nil :o]
                                 [nil :o nil]
                                 [nil :o :o]])))
    (is (= :x
           (sut/diagonal-winner [[:x nil :o]
                                 [nil :x nil]
                                 [nil :o :x]])))
    (is (nil?
         (sut/diagonal-winner [[:o nil :o]
                               [:o nil nil]
                               [nil :o :o]]))))

  (testing "full nil diagonal returns nil"
    (is (nil?
         (sut/diagonal-winner [[nil nil :o]
                               [:o nil nil]
                               [nil :o nil]])))))

(deftest anti-diagonal-winner-test
  (testing "returns the anti-diagonal winner of a board, else nil"
    (is (= :o
           (sut/anti-diagonal-winner [[:o nil :o]
                                      [nil :o nil]
                                      [:o :o nil]])))
    (is (= :x
           (sut/anti-diagonal-winner [[:o nil :x]
                                      [nil :x nil]
                                      [:x :o nil]])))
    (is (nil?
         (sut/anti-diagonal-winner [[:o nil :o]
                                    [:o nil nil]
                                    [nil :o :o]])))))

(deftest winner-test
  (testing "no winner for default board"
    (is (= :no-winner
           (sut/winner sut/default-board))))

  (testing "O wins horizontal"
    (is (= :o
           (sut/winner [[:o  :o :o]
                        [nil :x nil]
                        [:x nil :x]])))
    (is (= :o
           (sut/winner [[nil :x nil]
                        [:o :o :o]
                        [:o nil :o]])))
    (is (= :o
           (sut/winner [[nil :x nil]
                        [:o nil :o]
                        [:o :o :o]]))))

  (testing "X wins horizontal"
    (is (= :x
           (sut/winner [[:x  :x :x]
                        [nil :o nil]
                        [:o nil :o]])))
    (is (= :x
           (sut/winner [[nil :o nil]
                        [:x :x :x]
                        [:o nil :o]])))
    (is (= :x
           (sut/winner [[nil :o nil]
                        [:o nil :o]
                        [:x :x :x]])))
    (is (= :x
           (sut/winner [[nil nil nil]
                        [:o nil :o]
                        [:x :x :x]])))))

(deftest winner-test-vertical
  (testing "O wins vertical"
    (is (= :o
           (sut/winner [[nil nil :o]
                        [nil :x :o]
                        [nil :x :o]])))
    (is (= :o
           (sut/winner [[nil :o :x]
                        [nil :o nil]
                        [nil :o nil]])))
    (is (= :o
           (sut/winner [[:o nil :x]
                        [:o :x nil]
                        [:o :x nil]]))))

  (testing "X wins vertical"
    (is (= :x
           (sut/winner [[nil nil :x]
                        [nil :o :x]
                        [nil :o :x]])))
    (is (= :x
           (sut/winner [[:x nil :x]
                        [:x :x nil]
                        [:x :x nil]])))
    (is (= :x
           (sut/winner [[nil :x :x]
                        [nil :x nil]
                        [nil :x nil]])))))

;; TODO Add test of invalid state of board

(deftest winner-test-diagonal
  (testing "O wins diagonal"
    (is (= :o
           (sut/winner [[:o nil :x]
                        [nil :o nil]
                        [:o :x :o]]))))

  (testing "X wins diagonal"
    (is (= :x
           (sut/winner [[:x nil :x]
                        [nil :x nil]
                        [:o :x :x]]))))

  (testing "nils do not win diagonal"
    (is (= :no-winner
           (sut/winner [[nil nil :x]
                        [nil nil :x]
                        [:o :x nil]])))))

(deftest winner-test-anti-diagonal
  (testing "O wins anti-diagonal"
    (is (= :o
           (sut/winner [[:o nil :o]
                        [nil :o nil]
                        [:o :x :x]]))))

  (testing "X wins anti-diagonal"
    (is (= :x
           (sut/winner [[nil nil :x]
                        [nil :x nil]
                        [:x nil :x]]))))

  (testing "nils do not win anti-diagonal"
    (is (= :no-winner
           (sut/winner [[nil :o nil]
                        [:o nil :x]
                        [nil nil :x]])))))

(deftest no-winner
  (is (= :no-winner
         (sut/winner [[nil :o :x]
                      [:o :x :o]
                      [nil :x :o]])))
  (is (= :no-winner
         (sut/winner [[:x :o :x]
                      [:o :x :o]
                      [:o :x :o]])))
  (is (= :no-winner
         (sut/winner [[:o :x :o]
                      [:o :x :o]
                      [:x :o :x]]))))

(deftest bigger-board
  (let [big-board (sut/board 4)]
    (is (= :x
           (sut/winner [[:x :x :o :x]
                        [:o :x :x :o]
                        [:o :x :x :x]
                        [:o :o :x :x]])))

    (is (= :x
           (sut/winner [[:x :x :x :x]
                        [:o :x :x :x]
                        [:x :x :o :x]
                        [:o :o :x :o]])))
    (is (= :no-winner
           (sut/winner big-board)))))

(deftest connect-4-test
  (is (= :x
         (sut/winner [[:o :x :x :x]])))

  (testing "diagonal"
    (is (= :o
           (sut/winner [[:o :x :o :x]
                        [:x :o nil :x]
                        [:o :x :o nil]
                        [:x :o :x nil]]))))

  (testing "anti-diagonal"
    (is (= :x
           (sut/winner [[:o :x :o :x]
                        [:x nil :x :x]
                        [:o :x :o nil]
                        [:x :o :x nil]])))))
