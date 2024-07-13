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
                            [:o nil nil]
                            [:o nil :o]])))
    (is (= :o
           (sut/row-winner [[nil :o nil]
                            [:o :o :o]
                            [:o nil :o]])))
    (is (= :o
           (sut/row-winner [[nil :o :o]
                            [:o nil nil]
                            [:o :o :o]])))
    (is (= :o
           (sut/row-winner [[nil :o :o]
                            [nil nil nil]
                            [:o :o :o]])))
    (is (nil?
         (sut/row-winner [[nil :o :o]
                          [nil :o nil]
                          [:o nil :o]])))))

(deftest column-winner-test
  (testing "returns the column winner of a board, else nil"
    (is (= :o
           (sut/column-winner [[:o :o nil]
                               [:o :o :o]
                               [:o nil :o]])))
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
    (is (nil?
         (sut/diagonal-winner [[:o nil :o]
                               [:o nil nil]
                               [nil :o :o]])))))

(deftest anti-diagonal-winner-test
  (testing "returns the anti-diagonal winner of a board, else nil"
    (is (= :o
           (sut/anti-diagonal-winner [[:o nil :o]
                                      [nil :o nil]
                                      [:o :o nil]])))
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
                        [:o nil :o]]))))

  (testing "X wins horizontal"
    (is (= :x
           (sut/winner [[:x  :x :x]
                        [nil :o nil]
                        [:o nil :o]])))

    (is (= :x
           (sut/winner [[nil :o nil]
                        [:x :x :x]
                        [:o nil :o]])))))

(deftest winner-test-vertical
  (testing "O wins vertical"
    (is (= :o
           (sut/winner [[nil nil :o]
                        [nil :x :o]
                        [nil :x :o]])))

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
                        [:x :x nil]])))))

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
                        [:o :x :x]])))))

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
                        [:x nil :x]])))))

(deftest no-winner
  (is (= :no-winner
         (sut/winner [[:x :o :x]
                      [:o :x :o]
                      [:o :x :o]])))
  (is (= :no-winner
         (sut/winner [[:o :x :o]
                      [:o :x :o]
                      [:x :o :x]]))))
