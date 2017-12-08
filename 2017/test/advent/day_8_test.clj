(ns advent.day-8-test
  (:require [advent.day-8 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-8.txt")))

(def example "b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10")

(t/deftest part-a
  (t/is (= 1 (sut/max-reg (sut/execute (sut/parse example)))))
  (t/is (= 6611 (sut/max-reg (sut/execute (sut/parse input))))))
