(ns adventofcode.day-2
  (:require [clojure.string :refer [split]]))

(defn required-wrapping-paper [w h l]
  (let [sides [(* l w)
               (* w h)
               (* h l)]
        multiplied (map (partial * 2) sides)
        smallest (apply min sides)]
    (apply + (conj multiplied smallest))))

(defn parse-measurement [measurement]
  (map #(Integer. %) (split measurement #"x")))

(defn total-wrapping-paper [input]
  (let [lines (split input #"\n")
        measurements (map parse-measurement lines)
        requirements (map (partial apply required-wrapping-paper) measurements)]
    (apply + requirements)))
