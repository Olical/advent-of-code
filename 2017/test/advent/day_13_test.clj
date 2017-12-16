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
  (t/is (= 24 (:severity (sut/severity example {}))))
  (t/is (= 1704 (:severity (sut/severity input {})))))

(t/deftest part-b
  (t/is (= 10 (sut/stealth-delay example)))
  (t/is (= 3970918 (sut/stealth-delay input))))
