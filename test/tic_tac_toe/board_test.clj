(ns tic-tac-toe.board-test
  (:require [tic-tac-toe.board :as sut]
            [clojure.test :refer [deftest is testing]]))

(deftest default-board-test
  (testing "default board is 3x3 nils"
    (is (= sut/default-board
           [[nil nil nil]
            [nil nil nil]
            [nil nil nil]]))))
