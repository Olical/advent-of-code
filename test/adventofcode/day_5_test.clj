(ns adventofcode.day-5-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-5 :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def input (string/split (slurp (io/resource "inputs/day-5.txt")) #"\n"))

(deftest example-input
  (is (is-nice? "ugknbfddgicrmopn"))
  (is (is-nice? "aaa"))
  (is (not (is-nice? "jchzalrnumimnmhp")))
  (is (not (is-nice? "haegwjzuvuyypxyu")))
  (is (not (is-nice? "dvszwmarrgswjxmb"))))

(deftest part-1
  (is (= nil (count-nice input))))
