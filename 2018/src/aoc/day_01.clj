(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn sum-lines []
  (with-open [rdr (io/reader "inputs/day-01.txt")]
    (->> (line-seq rdr)
         (map edn/read-string)
         (reduce +))))

(defn first-dupe []
  (with-open [rdr (io/reader "inputs/day-01.txt")]
    (loop [freqs (->> (line-seq rdr) (map edn/read-string) (cycle))
           sums #{0}
           sum 0]
      (let [sum (+ sum (first freqs))]
        (if (contains? sums sum)
          sum
          (recur (rest freqs) (conj sums sum) sum))))))

(t/deftest tests
  (t/testing "01 A"
    (t/is (= (sum-lines) 510)))

  (t/testing "01 B"
    (t/is (= (first-dupe) 69074))))
