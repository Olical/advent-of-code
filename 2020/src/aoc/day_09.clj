(ns aoc.day-09
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [clojure.math.combinatorics :as combi]
            [aoc.core :as aoc]))

(def input (aoc/with-lines "day-09" edn/read-string vec))

(defn weakness-precursor [n xs]
  (loop [prev (vec (take n xs))
         xs (drop n xs)]
    (let [[x & xs] xs
          sums (into #{} (map #(apply + %) (combi/combinations prev 2)))]
      (if (contains? sums x)
        (recur
          (conj (subvec prev 1) x)
          xs)
        x))))

(defn weakness [target xs]
  (loop [sum (first xs)
         window [(first xs)]
         xs (rest xs)]
    (cond
      (= sum target) (let [result (sort window)]
                       (+ (first result) (last result)))
      (> sum target) (recur (- sum (first window))
                            (subvec window 1)
                            xs)
      (< sum target) (recur (+ sum (first xs))
                            (conj window (first xs))
                            (rest xs)))))

(t/deftest day-09-a
  (t/is (= 373803594 (weakness-precursor 25 input))))

(t/deftest day-09-b
  (t/is (= 51152360 (weakness 373803594 input))))
