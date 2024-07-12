(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.board :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest default-board-test
  (testing "default board of size 3x3 nils"
    (is (= sut/default-board
           [[0 0 0]
            [0 0 0]
            [0 0 0]]))))


(deftest winner-test
  (testing "no winner for default board"
    (is (nil? (sut/winner sut/default-board))))

  (testing "1 wins horizontal"
    (is (= (sut/winner [[1  1 1]
                        [0 -1 0]
                        [-1 0 -1]])
           1))

    (is (= (sut/winner [[0 -1 0]
                        [1 1 1]
                        [1 0 1]])
           1)))

  (testing "-1 wins horizontal"
    (is (= -1 (sut/winner [[-1  -1 -1]
                           [0 1 0]
                           [1 0 1]])))

    (is (= -1 (sut/winner [[0 1 0]
                           [-1 -1 -1]
                           [1 0 1]])))))
