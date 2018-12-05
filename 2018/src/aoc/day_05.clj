(ns aoc.day-05
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(set! *print-length* 20)

(defn units []
  (aoc/with-input "day-05"
    (fn [input]
      (mapv str (drop-last (slurp input))))))

(defn lower [s]
  (when (string? s)
    (str/lower-case s)))

(defn react [units]
  (loop [units units
         acc []]
    (if-let [unit (first units)]
      (let [prev (last acc)
            units (subvec units 1)]
        (if (and (not= unit prev) (= (lower unit) (lower prev)))
          (recur units (pop acc))
          (recur units (conj acc unit))))
      acc)))

(defn reacted-count []
  (count (react (units))))

(def alphabet (str/split "abcdefghijklmnopqrstuvwxyz" #""))

(defn reacted-shortest-count []
  (let [base (react (units))]
    (->> alphabet
         (map
           (fn [target]
             (count (react (vec (remove #(or (= target %) (= target (lower %))) base))))))
         (sort)
         (first))))

(t/deftest day-05-a
  (t/testing "input"
    (t/is (= (reacted-count) 9822))))

(t/deftest day-05-b
  (t/testing "input"
    (t/is (= (reacted-shortest-count) 5726))))
