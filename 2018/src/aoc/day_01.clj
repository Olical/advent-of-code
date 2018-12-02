(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [aoc.core :as aoc]))

(defn sum-lines []
  (aoc/with-lines "day-01" edn/read-string
    #(reduce + %)))

(defn first-dupe []
  (aoc/with-lines "day-01" edn/read-string
    (fn [freqs]
      (loop [freqs (cycle freqs)
             sums #{}
             sum 0]
        (let [sum (+ sum (first freqs))]
          (if (contains? sums sum)
            sum
            (recur (rest freqs) (conj sums sum) sum)))))))

(t/deftest day-01-a
  (t/testing "input"
    (t/is (= (sum-lines) 510))))

(t/deftest day-01-b
  (t/testing "input"
    (t/is (= (first-dupe) 69074))))
