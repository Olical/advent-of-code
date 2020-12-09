(ns aoc.day-09
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [clojure.math.combinatorics :as combi]
            [aoc.core :as aoc]))

(def input (aoc/with-lines "day-09" edn/read-string vec))

(defn find-weakness [n xs]
  (loop [prev (vec (take n xs))
         xs (drop n xs)]
    (let [[x & xs] xs
          sums (into #{} (map #(apply + %) (combi/combinations prev 2)))]
      (if (contains? sums x)
        (recur
          (conj (subvec prev 1) x)
          xs)
        x))))

(t/deftest day-09-a
  (t/is (= 373803594 (find-weakness 25 input))))

(t/deftest day-09-b
  (t/is (= 0 0)))
