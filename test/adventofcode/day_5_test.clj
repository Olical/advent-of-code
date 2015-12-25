(ns adventofcode.day-5-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-5 :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def input (string/split (slurp (io/resource "inputs/day-5.txt")) #"\n"))

(deftest example-input
  (is (is-nice-1? "ugknbfddgicrmopn"))
  (is (is-nice-1? "aaa"))
  (is (not (is-nice-1? "jchzalrnumimnmhp")))
  (is (not (is-nice-1? "haegwjzuvuyypxyu")))
  (is (not (is-nice-1? "dvszwmarrgswjxmb"))))

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

(deftest has-pairs-test
  (is (has-pair? "qjhvhtzxzqqjkmpb"))
  (is (has-pair? "xxyxx"))
  (is (has-pair? "uurcxstgmygtbstg"))
  (is (not (has-pair? "ieodomkazucvgmuy"))))

(deftest has-repeats-test
  (is (has-repeat-with-gap? "qjhvhtzxzqqjkmpb"))
  (is (has-repeat-with-gap? "xxyxx"))
  (is (not (has-repeat-with-gap? "uurcxstgmygtbstg")))
  (is (has-repeat-with-gap? "ieodomkazucvgmuy")))

(deftest example-input-2
  (is (is-nice-2? "qjhvhtzxzqqjkmpb"))
  (is (is-nice-2? "xxyxx"))
  (is (not (is-nice-2? "uurcxstgmygtbstg")))
  (is (not (is-nice-2? "ieodomkazucvgmuy"))))

(deftest part-1
  (is (= 238 (countp is-nice-1? input))))

(deftest part-2
  (is (= nil (countp is-nice-2? input))))
