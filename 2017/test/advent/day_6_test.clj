(ns advent.day-6-test
  (:require [advent.day-6 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-5.txt")))
(def example "0 2 7 0")

(t/deftest part-a
  #_(t/is (= 5 (sut/redistributions example)))
  #_(t/is (= nil (sut/redistributions input))))
