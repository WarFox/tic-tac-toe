(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.board :as sut]
            [clojure.test :refer [deftest is testing]]))

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

(deftest winner-test
  (testing "no winner for default board"
    (is (nil? (sut/winner sut/default-board))))

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
    (is (= -1 (sut/winner [[-1  -1 -1]
                           [0 1 0]
                           [1 0 1]])))

    (is (= -1 (sut/winner [[0 1 0]
                           [-1 -1 -1]
                           [1 0 1]])))))

(deftest winner-test-vertical
  (testing "1 wins vertical"
    (is (= 1 (sut/winner [[1 0 -1]
                          [1 -1 0]
                          [1 -1 0]]))))

  (testing "-1 wins vertical"
    (is (= 1 (sut/winner [[1 0 -1]
                          [1 -1 0]
                          [1 -1 0]])))))
