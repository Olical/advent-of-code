(ns adventofcode.day-3-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-3 :refer :all]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "inputs/day-3.txt")))

(deftest basic-examples
  (is (= 2 (count-houses ">")))
  (is (= 4 (count-houses "^>v<")))
  (is (= 2 (count-houses "^v^v^v^v^v"))))
