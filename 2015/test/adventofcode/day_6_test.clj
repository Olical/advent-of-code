(ns adventofcode.day-6-test
  (:require [clojure.test :refer :all]
            [adventofcode.day-6 :refer :all]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def input (string/split (slurp (io/resource "inputs/day-6.txt")) #"\n"))

(deftest day-1
  (is (= 543903 (count-enabled-lights input))))

(deftest grid-tools
  (is (= 0 (xy->i 0 0)))
  (is (= 10 (xy->i 10 0)))
  (is (= 1000 (xy->i 0 1)))
  (is (= :off (get-light {} 5 8)))
  (let [lights (set-light {} 5 8 :on)]
    (is (= :off (get-light lights 4 2)))
    (is (= :on (get-light lights 5 8))))
  (let [lights (set-light {} 2 4 :toggle)
        inverted (set-light {} 2 4 :toggle)]
    (is (= :on (get-light lights 2 4))
        (= :off (get-light inverted 2 4)))))

(deftest rule-parsing
  (is (= {:x 0 :y 0} (parse-coord "0,0")))
  (is (= {:x 2 :y 5} (parse-coord "2,5")))
  (is (= {:x 10 :y 200} (parse-coord "10,200")))
  (is (= {:from {:x 0 :y 0}
          :to {:x 999 :y 999}
          :state :on}
         (parse-rule "turn on 0,0 through 999,999")))
  (is (= {:from {:x 0 :y 0}
          :to {:x 999 :y 0}
          :state :toggle}
         (parse-rule "toggle 0,0 through 999,0")))
  (is (= {:from {:x 499 :y 499}
          :to {:x 500 :y 500}
          :state :off}
         (parse-rule "turn off 499,499 through 500,500"))))
