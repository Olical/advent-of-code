(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.math.combinatorics :as combi]
            [aoc.core :as aoc]))

(def sum-target 2020)

;; Sorting the input makes this a lot faster!
(def input (aoc/with-lines "day-01" #(Integer/parseInt %) sort))

(defn pair-sum-2020-product [xs]
  (some
    (fn [a]
      (some
        (fn [b]
          (and (not= a b)
               (= sum-target (+ a b))
               (* a b)))
        xs))
    xs))

(defn triplet-sum-2020-product [xs]
  (some
    (fn [a]
      (some
        (fn [b]
          (some
            (fn [c]
              (and (not= a b c)
                   (= sum-target (+ a b c))
                   (* a b c)))
            xs))
        xs))
    xs))

(defn n-sum-target-product [target n xs]
  (some
    (fn [group]
      (when (= target (reduce + group))
        (reduce * group)))
    (combi/combinations xs n)))

(t/deftest day-01-a
  (t/is (= 1009899 (pair-sum-2020-product input)))
  (t/is (= 1009899 (n-sum-target-product sum-target 2 input))))

(t/deftest day-01-b
  (t/is (= 44211152 (triplet-sum-2020-product input)))
  (t/is (= 44211152 (n-sum-target-product sum-target 3 input))))
