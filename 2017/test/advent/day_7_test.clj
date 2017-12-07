(ns advent.day-7-test
  (:require [advent.day-7 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-7.txt")))

(def example "pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)")

(t/deftest part-a
  (t/is (= "tknk" (sut/root (sut/parse example))))
  (t/is (= "bsfpjtc" (sut/root (sut/parse input)))))

(t/deftest part-b
  (t/is (= 60 (sut/fix-weight (sut/parse example))))
  #_(t/is (= nil (sut/fix-weight (sut/parse input)))))
