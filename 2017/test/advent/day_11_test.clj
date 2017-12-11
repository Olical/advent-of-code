(ns advent.day-11-test
  (:require [advent.day-11 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-11.txt")))

(t/deftest part-a
  (t/is (= 3 (sut/distance "ne,ne,ne")))
  (t/is (= 0 (sut/distance "ne,ne,sw,sw")))
  (t/is (= 2 (sut/distance "ne,ne,s,s")))
  (t/is (= 3 (sut/distance "se,sw,se,sw,sw")))
  (t/is (= nil (sut/distance input))))
