(ns adventofcode.day-2-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-2 :refer :all]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "inputs/day-2.txt")))

;; Find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l.
;; The elves also need a little extra paper for each present: the area of the smallest side.

(deftest example-results
  (is (= 58 (required-wrapping-paper 2 3 4)))
  (is (= 43 (required-wrapping-paper 1 1 10)))
  (is (= 0 (required-wrapping-paper 0 0 0))))

(deftest parsing-measurements
  (is (= [1 2 3] (parse-measurement "1x2x3"))))

(deftest total-required
  (is (= 1598415 (total-wrapping-paper input))))
