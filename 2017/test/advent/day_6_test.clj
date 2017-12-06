(ns advent.day-6-test
  (:require [advent.day-6 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-6.txt")))
(def example [0 2 7 0])

(t/deftest part-a
  (t/is (= 5 (sut/redistributions example :single)))
  (t/is (= 3156 (sut/redistributions (sut/parse input) :single))))

(t/deftest part-b
  (t/is (= 4 (sut/redistributions example :infinite)))
  (t/is (= 1610 (sut/redistributions (sut/parse input) :infinite))))
