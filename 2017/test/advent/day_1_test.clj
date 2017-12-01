(ns advent.day-1-test
  (:require [clojure.test :as t]
            [advent.day-1 :as sut]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-1.txt")))

(t/deftest examples
  (t/is (= 3 (sut/sum-pairs "1122")))
  (t/is (= 4 (sut/sum-pairs "1111")))
  (t/is (= 0 (sut/sum-pairs "1234")))
  (t/is (= 9 (sut/sum-pairs "91212129"))))

(t/deftest answer
  (t/is (= 1341 (sut/sum-pairs input))))
