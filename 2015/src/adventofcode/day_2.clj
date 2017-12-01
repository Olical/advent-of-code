(ns adventofcode.day-2
  (:require [clojure.string :refer [split]]))

(defn required-wrapping-paper [w h l]
  (let [sides [(* l w)
               (* w h)
               (* h l)]
        multiplied (map (partial * 2) sides)
        smallest (apply min sides)]
    (apply + (conj multiplied smallest))))

(defn required-ribbon [w h l]
  (let [perimeters (map (partial * 2) [(+ l w)
                                       (+ w h)
                                       (+ h l)])
        smallest (apply min perimeters)]
    (+ smallest (* w h l))))

(defn parse-measurement [measurement]
  (map #(Integer. %) (split measurement #"x")))

(defn parse-input [input]
  (map parse-measurement (split input #"\n")))

(defn map-total-requirements [input f]
  (let [measurements (parse-input input)
        requirements (map (partial apply f) measurements)]
    (apply + requirements)))

(defn total-wrapping-paper [input]
  (map-total-requirements input required-wrapping-paper))

(defn total-ribbon [input]
  (map-total-requirements input required-ribbon))
