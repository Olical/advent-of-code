(ns adventofcode.day-5
  (:require [clojure.string :as string]))

(def vowles #{\a \e \i \o \u})

(def bool-num {true 1
               false 0})

(def bad-substrs #{"ab"
                   "cd"
                   "pq"
                   "xy"})

(defn count-vowles [coll]
  (count (filter #(contains? vowles %) coll)))

(defn has-sequential? [coll]
  (boolean (re-find #"(.)\1" coll)))

(defn has-bad-substr? [coll]
  (boolean (some true? (map #(.contains coll %) bad-substrs))))

(defn has-pair? [coll]
  false)

(defn has-repeat-with-gap? [coll]
  (boolean (re-find #"(.).\1" coll)))

(defn is-nice-1? [input]
  (and
    (>= (count-vowles input) 3)
    (has-sequential? input)
    (not (has-bad-substr? input))))

(defn is-nice-2? [input]
  (and
    (has-pair? input)
    (has-repeat-with-gap? input)))

(defn countp [f input]
  (reduce + (map bool-num (map f input))))
