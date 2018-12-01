(ns aoc.day-01
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn sum-lines []
  (aoc/with-edn-lines freqs "day-01"
    (reduce + freqs)))

(defn first-dupe []
  (aoc/with-edn-lines freqs "day-01"
    (loop [freqs (cycle freqs)
           sums #{}
           sum 0]
      (let [sum (+ sum (first freqs))]
        (if (contains? sums sum)
          sum
          (recur (rest freqs) (conj sums sum) sum))))))

(t/deftest day-01-a
  (t/testing "input"
    (t/is (= (sum-lines) 510))))

(t/deftest day-01-b
  (t/testing "input"
    (t/is (= (first-dupe) 69074))))
