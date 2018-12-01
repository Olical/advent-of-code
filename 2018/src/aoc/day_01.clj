(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn with-freqs [f]
  (with-open [rdr (io/reader "inputs/day-01.txt")]
    (f (map edn/read-string (line-seq rdr)))))

(defn sum-lines []
  (with-freqs #(reduce + %)))

(defn first-dupe []
  (with-freqs
    (fn [freqs]
      (loop [freqs (cycle freqs)
             sums #{}
             sum 0]
        (let [sum (+ sum (first freqs))]
          (if (contains? sums sum)
            sum
            (recur (rest freqs) (conj sums sum) sum)))))))

(t/deftest tests
  (t/testing "01 A"
    (t/is (= (sum-lines) 510)))

  (t/testing "01 B"
    (t/is (= (first-dupe) 69074))))
