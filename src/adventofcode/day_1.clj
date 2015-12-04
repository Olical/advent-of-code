(ns adventofcode.day-1
  (:require [clojure.java.io :as io]))

(def instruction-values
  {\( 1
   \) -1})

(defn parse-instructions [instructions]
  (map instruction-values instructions))

(defn add-to-acc [acc x]
  (conj acc (+ x (last acc))))

(defn get-floors [instructions]
  (rest (reduce add-to-acc [0] (parse-instructions instructions))))
