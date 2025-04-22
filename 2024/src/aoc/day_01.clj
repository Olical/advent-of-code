(ns aoc.day-01
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [malli.experimental :as mx]))

(def input-resource (io/resource "aoc/day_01.txt"))

(mx/defn parse-row :- [:sequential pos-int?]
  [row :- string?]
  (map parse-long (str/split row #"\s+")))

(mx/defn difference :- int?
  [a :- pos-int?
   b :- pos-int?]
  (Math/abs (- a b)))

(mx/defn part-1 :- pos-int?
  [input]
  (let [lines (line-seq (io/reader input))
        pairs (map parse-row lines)
        left (sort (map first pairs))
        right (sort (map second pairs))
        differences (map difference left right)]
    (reduce + differences)))
