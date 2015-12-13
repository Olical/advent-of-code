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

(deftest has-sequential-test
  (is (has-sequential? "hello world"))
  (is (not (has-sequential? "oh hey"))))

(deftest count-vowles-test
  (is (= 3 (count-vowles "aaa")))
  (is (= 5 (count-vowles "xxx a eio u xxx")))
  (is (= 0 (count-vowles "pppffft")))
  (is (= 0 (count-vowles "")))
  (is (= 1 (count-vowles "dvszwmarrgswjxmb"))))

(deftest has-bad-substr-test
  (is (not (has-bad-substr? "hello")))
  (is (has-bad-substr? "well ab then")))

(deftest part-1
  (is (= 238 (count-nice input))))
