(ns aoc.day-11
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(t/deftest day-11-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-11-b
  (t/testing "input"
    (t/is (= 0 0))))

(def grid-serial 57 #_9110)

; (for [x (range 1 301)
;       y (range 1 301)])

(defn power [x y]
  (let [rack (+ x 10)]
    (-> (* rack y)
        (+ grid-serial)
        (* rack)
        (/ 100)
        (mod 10)
        (- 5)
        (int))))

(power 122 79)
