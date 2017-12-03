(ns advent.day-2-test
  (:require [advent.day-2 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-2.txt")))

(def example "5 1 9 5
7 5 3
2 4 6 8")

(t/deftest part-a
  (t/is (= 18 (sut/checksum example)))
  (t/is (= nil (sut/checksum input))))
