(ns adventofcode.day-4-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-4 :refer :all]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "inputs/day-4.txt")))

(deftest example-input-part-1
  (is (= 609043 (mine-advent 5 "abcdef")))
  (is (= 1048970 (mine-advent 5 "pqrstuv"))))

(deftest starts-with-zeros-test
  (is (= true (starts-with-zeros 5 "00000hello")))
  (is (= false (starts-with-zeros 5 "100000hey"))))

(deftest real-input-day-1
  (is (= 346386 (mine-advent 5 input))))

(deftest real-input-day-2
  (is (= 9958218 (mine-advent 6 input))))
