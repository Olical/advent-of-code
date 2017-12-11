(ns advent.day-11-test
  (:require [advent.day-11 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-11.txt")))

(t/deftest part-a
  (t/is (= 3 (:last (sut/distance "ne,ne,ne"))))
  (t/is (= 0 (:last (sut/distance "ne,ne,sw,sw"))))
  (t/is (= 2 (:last (sut/distance "ne,ne,s,s"))))
  (t/is (= 3 (:last (sut/distance "se,sw,se,sw,sw"))))
  (t/is (= 664 (:last (sut/distance input)))))

(t/deftest part-b
  (t/is (= 1447 (:furthest (sut/distance input)))))
