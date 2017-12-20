(ns advent.day-18-test
  (:require [advent.day-18 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (sut/parse (slurp (io/resource "day-18.txt"))))

(t/deftest part-a
  (t/is (= 1187 (sut/freq input))))
