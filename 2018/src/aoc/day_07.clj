(ns aoc.day-07
  (:require [clojure.test :as t]
            [clojure.set :as set]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(def step-re #"Step (\w) must be finished before step (\w) can begin.")

(defn parse-step [s]
  (->> s
       (re-matches step-re)
       (rest)
       (mapv keyword)))

(def input
  (aoc/with-lines "day-07" parse-step vec))

(def steps
  (into #{} (mapcat identity) input))

(def deps
  (->> input
       (mapv (fn [[a b]] {b #{a}}))
       (apply merge-with
              set/union
              (zipmap steps (repeat #{})))))

(defn available [completed steps]
  (->> steps
       (filter
         (fn [step]
           (set/superset? completed (get deps step))))
       (sort)
       (first)))

(defn step-string []
  (loop [steps steps
         acc []]
    (if-let [step (available (set acc) steps)]
      (recur (disj steps step) (conj acc step))
      (->> acc (map name) (str/join)))))

(t/deftest day-07-a
  (t/testing "input"
    (t/is (= (step-string) "GJKLDFNPTMQXIYHUVREOZSAWCB"))))

(t/deftest day-07-b
  (t/testing "input"
    (t/is (= 0 0))))
