(ns advent.day-10-test
  (:require [advent.day-10 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-10.txt")))

(t/deftest part-a
  (t/is (= 12 (sut/twist "3,4,1,5" 5)))
  (t/is (= 1980 (sut/twist input 256))))

(t/deftest part-b
  (t/is (= "a2582a3a0e66e6e86e3812dcb672a272" (sut/knot-hash "")))
  (t/is (= "33efeb34ea91902bb2f59c9920caa6cd" (sut/knot-hash "AoC 2017")))
  (t/is (= "3efbe78a8d82f29979031a4aa0b16a9d" (sut/knot-hash "1,2,3")))
  (t/is (= "63960835bcdc130f0b66d7ff4f6a5a8e" (sut/knot-hash "1,2,4"))))
