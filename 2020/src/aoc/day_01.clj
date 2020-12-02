(ns aoc.day-01
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

;; TODO Generic way to express the amount of distinct values we're looking for.
;; TODO Can this be done in one pass or does it require nested loops?

(def sum-target 2020)
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

(t/deftest day-01-a
  (t/is (= 1009899 (pair-sum-2020-product input))))

(t/deftest day-01-b
  (t/is (= 44211152 (triplet-sum-2020-product input))))
