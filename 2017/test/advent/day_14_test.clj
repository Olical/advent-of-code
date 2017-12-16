(ns advent.day-14-test
  (:require [advent.day-14 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (str/trim (slurp (io/resource "day-14.txt"))))

(def example "flqrgnkx")

(t/deftest part-a
  (t/is (= 8108 (count (filter :used? (sut/memory example)))))
  (t/is (= 8106 (count (filter :used? (sut/memory input))))))
