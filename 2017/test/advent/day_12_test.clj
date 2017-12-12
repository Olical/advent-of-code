(ns advent.day-12-test
  (:require [advent.day-12 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (sut/parse (slurp (io/resource "day-12.txt"))))

(def example (sut/parse "0 <-> 2
1 <-> 1
2 <-> 0, 3, 4
3 <-> 2, 4
4 <-> 2, 3, 6
5 <-> 6
6 <-> 4, 5"))

(t/deftest part-a
  (t/is (= 6 (count (sut/group example 0))))
  (t/is (= 130 (count (sut/group input 0)))))
