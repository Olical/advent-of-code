(ns advent.day-13-test
  (:require [advent.day-13 :as sut]
            [clojure.test :as t]))

(def example (sut/parse "0: 3
1: 2
4: 4
6: 4"))

example

(t/deftest part-a
  (t/is (= 24 (sut/severity example))))
