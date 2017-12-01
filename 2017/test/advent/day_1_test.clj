(ns advent.day-1-test
  (:require [clojure.test :as t]
            [advent.day-1 :as sut]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-1.txt")))

(t/deftest part-a
  (t/is (= 3 (sut/sum-pairs "1122" :next)))
  (t/is (= 4 (sut/sum-pairs "1111" :next)))
  (t/is (= 0 (sut/sum-pairs "1234" :next)))
  (t/is (= 9 (sut/sum-pairs "91212129" :next)))
  (t/is (= 1341 (sut/sum-pairs input :next))))

(t/deftest part-b
  (t/is (= 6 (sut/sum-pairs "1212" :halfway)))
  (t/is (= 0 (sut/sum-pairs "1221" :halfway)))
  (t/is (= 4 (sut/sum-pairs "123425" :halfway)))
  (t/is (= 12 (sut/sum-pairs "123123" :halfway)))
  (t/is (= 4 (sut/sum-pairs "12131415" :halfway)))
  (t/is (= 1348 (sut/sum-pairs input :halfway))))
