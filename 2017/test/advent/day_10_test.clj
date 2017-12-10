(ns advent.day-10-test
  (:require [advent.day-10 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-10.txt")))

(t/deftest part-a
  (t/is (= 12 (sut/twist-hash "3,4,1,5" 5)))
  (t/is (= 1980 (sut/twist-hash input 256))))
