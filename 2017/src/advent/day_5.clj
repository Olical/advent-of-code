(ns advent.day-5
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (mapv edn/read-string)))

(defn weird-inc [n]
  (if (>= n 3)
    (dec n)
    (inc n)))

(defn steps [src update-fn]
  (loop [instructions (transient (parse src))
         pointer 0
         step 0]
    (if-let [instruction (get instructions pointer)]
      (recur (assoc! instructions pointer (update-fn instruction))
             (+ pointer instruction)
             (inc step))
      step)))
