(ns advent.day-4-test
  (:require [advent.day-4 :as sut]
            [clojure.java.io :as io]
            [clojure.test :as t]))

(def input (slurp (io/resource "day-4.txt")))

(t/deftest part-a
  (t/is (= 1 (sut/valid-phrase-count "aa bb cc dd ee")))
  (t/is (= 1 (sut/valid-phrase-count "aa bb cc dd aaa")))
  (t/is (= 0 (sut/valid-phrase-count "aa bb cc dd aa")))
  (t/is (= 451 (sut/valid-phrase-count input))))
