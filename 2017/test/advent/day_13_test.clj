(ns advent.day-13-test
  (:require [advent.day-13 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (sut/parse (slurp (io/resource "day-13.txt"))))

(def example (sut/parse "0: 3
1: 2
4: 4
6: 4"))

(t/deftest part-a
  (t/is (= 24 (sut/severity example))))
