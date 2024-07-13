(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.board :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest board-test
  (testing "new board"
    (is (= (sut/board 2)
           [[0 0]
            [0 0]]))))

(deftest default-board-test
  (testing "default board of size 3x3 0's"
    (is (= sut/default-board
           [[0 0 0]
            [0 0 0]
            [0 0 0]]))))

(deftest sum-test
  (testing "returns sum of rows from a board"
    (is (= '(0 3 -3)
           (sut/sum-row [[0 0 0]
                         [1 1 1]
                         [-1 -1 -1]])))))

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
           (sut/diagonals [[1 0 -1]
                           [0 2 0]
                           [1 -1 3]])))))

(deftest anti-diagonals-test
  (testing "returns anti diagonal items"
    (is (= '(1 2 3)
           (sut/anti-diagonals [[0 0 3]
                                [0 2 0]
                                [1 -1 0]])))))

(deftest winner-test
  (testing "no winner for default board"
    (is (= :no-winner
           (sut/winner sut/default-board))))

  (testing "1 wins horizontal"
    (is (= 1
           (sut/winner [[1  1 1]
                        [0 -1 0]
                        [-1 0 -1]])))

    (is (= 1
           (sut/winner [[0 -1 0]
                        [1 1 1]
                        [1 0 1]]))))

  (testing "-1 wins horizontal"
    (is (= -1
           (sut/winner [[-1  -1 -1]
                        [0 1 0]
                        [1 0 1]])))

    (is (= -1
           (sut/winner [[0 1 0]
                        [-1 -1 -1]
                        [1 0 1]])))))

(deftest winner-test-vertical
  (testing "1 wins vertical"
    (is (= 1
           (sut/winner [[0 0 1]
                        [0 -1 1]
                        [0 -1 1]])))

    (is (= 1
           (sut/winner [[1 0 -1]
                        [1 -1 0]
                        [1 -1 0]]))))

  (testing "-1 wins vertical"
    (is (= -1
           (sut/winner [[0 0 -1]
                        [0 1 -1]
                        [0 1 -1]])))
    (is (= -1
           (sut/winner [[-1 0 -1]
                        [-1 -1 0]
                        [-1 -1 0]])))))

;; TODO Add test of invalid state of board

(deftest winner-test-diagonal
  (testing "1 wins diagonal"
    (is (= 1
           (sut/winner [[1 0 -1]
                        [0 1 0]
                        [1 -1 1]]))))

  (testing "-1 wins diagonal"
    (is (= -1
           (sut/winner [[-1 0 -1]
                        [0 -1 0]
                        [1 -1 -1]])))))

(deftest winner-test-anti-diagonal
  (testing "1 wins anti-diagonal"
    (is (= 1
           (sut/winner [[1 0 1]
                        [0 1 0]
                        [1 -1 -1]]))))

  (testing "-1 wins anti-diagonal"
    (is (= -1
           (sut/winner [[0 0 -1]
                        [0 -1 0]
                        [-1 0 -1]])))))

(deftest no-winner
  (is (= :no-winner
         (sut/winner [[-1 1 -1]
                      [1 -1 1]
                      [1 -1 1]])))
  (is (= :no-winner
         (sut/winner [[1 -1 1]
                      [1 -1 1]
                      [-1 1 -1]]))))
