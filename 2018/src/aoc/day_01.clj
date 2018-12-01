(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defmacro with-input [bind & body]
  `(with-open [rdr# (io/reader "inputs/day-01.txt")]
     (let [~bind (map edn/read-string (line-seq rdr#))]
       ~@body)))

(defn sum-lines []
  (with-input freqs
    (reduce + freqs)))

(defn first-dupe []
  (with-input freqs
    (loop [freqs (cycle freqs)
           sums #{}
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
