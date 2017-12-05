(ns advent.day-5-test
  (:require [advent.day-5 :as sut]
            [clojure.java.io :as io]
            [clojure.test :as t]))

(def input (slurp (io/resource "day-5.txt")))
(def example "0\n3\n0\n1\n-3")

(t/deftest part-a
  (t/is (= 5 (sut/steps example inc)))
  (t/is (= 359348 (sut/steps input inc))))

(t/deftest part-b
  (t/is (= 10 (sut/steps example sut/weird-inc)))
  (t/is (= 27688760 (sut/steps input sut/weird-inc))))
