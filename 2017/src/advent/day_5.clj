(ns advent.day-5
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (mapv edn/read-string)))

(defn steps [src]
  (loop [instructions (parse src)
         pointer 0
         step 0]
    (if-let [instruction (get instructions pointer)]
      (recur (update instructions pointer inc)
             (+ pointer instruction)
             (inc step))
      step)))
