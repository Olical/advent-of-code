(ns adventofcode.core-test
  (:require [clojure.test :refer :all]
            [adventofcode.core :refer :all]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "inputs/day-1.txt")))

(deftest empty-input
  (is (= [] (get-floors "")) "Empty input yields an empty vector"))

(deftest simple-input
  (testing "Various paren combos yeild correct floor vectors"
    (is (= [1 2 3 4 5 4 3 2 1 0] (get-floors "((((()))))")))
    (is (= [1 2 1 2 1 0 -1 -2] (get-floors "(()())))")))))

(deftest final-floor-of-input
  (is (= 280 (last (get-floors input))) "Last floor of input is 280"))

(deftest first-basement
  (is (= 1797 (+ 1 (.indexOf (get-floors input) -1))) "First index of -1 (basement) is 1797"))
