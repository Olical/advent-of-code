(ns advent.day-16-test
  (:require [advent.day-16 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(def input (str/trim (slurp (io/resource "day-16.txt"))))

(t/deftest part-a
  (t/is (= "baedc" (sut/dance "abcde" "s1,x3/4,pe/b")))
  (t/is (= "gkmndaholjbfcepi" (sut/dance "abcdefghijklmnop" input))))
