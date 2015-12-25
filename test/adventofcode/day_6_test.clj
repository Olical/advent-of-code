(ns adventofcode.day-6-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-6 :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def input (string/split (slurp (io/resource "inputs/day-6.txt")) #"\n"))

(deftest day-1
  (is (= nil (count-enabled-lights input))))

(deftest grid-tools
  (is (= 0 (xy->i 0 0)))
  (is (= 10 (xy->i 10 0)))
  (is (= 1000 (xy->i 0 1)))
  (is (= :off (get-light {} 5 8)))
  (let [lights (set-light {} 5 8 :on)]
    (is (= :off (get-light lights 4 2)))
    (is (= :on (get-light lights 5 8)))))
