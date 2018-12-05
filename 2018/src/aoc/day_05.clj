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

(defn react []
  (loop [units (units)
         acc []]
    (if-let [unit (first units)]
      (let [prev (last acc)
            units (subvec units 1)]
        (if (and (not= unit prev) (= (lower unit) (lower prev)))
          (recur units (pop acc))
          (recur units (conj acc unit))))
      acc)))

(t/deftest day-05-a
  (t/testing "input"
    (t/is (= (count (react)) 0))))

(t/deftest day-05-b
  (t/testing "input"
    (t/is (= 0 0))))
