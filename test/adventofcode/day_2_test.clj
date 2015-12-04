(ns adventofcode.day-2-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-2 :refer :all]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "inputs/day-2.txt")))

;; Find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l.
;; The elves also need a little extra paper for each present: the area of the smallest side.

(deftest example-results
  (is (= 52 (required-wrapping-paper 2 3 4)))
  (is (= 42 (required-wrapping-paper 1 1 10))))
