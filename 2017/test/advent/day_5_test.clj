(ns advent.day-5-test
  (:require [advent.day-5 :as sut]
            [clojure.java.io :as io]
            [clojure.test :as t]))

(def input (slurp (io/resource "day-5.txt")))

(t/deftest part-a
  (t/is (= 5 (sut/steps "0\n3\n0\n1\n-3")))
  (t/is (= 359348 (sut/steps input))))
