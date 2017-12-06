(ns advent.day-6
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\s+")
       (mapv edn/read-string)))

(defn max-index [coll]
  (.indexOf coll (apply max coll)))

(defn redistributions [src]
  (loop [banks (parse src)
         seen #{(hash banks)}
         steps 0]
    (let [bank-index (max-index banks)
          bank (nth banks bank-index)
          next-banks (assoc banks bank-index 0)
          next-hash (hash next-banks)]
      (if (contains? seen next-hash)
        steps
        (recur next-banks (conj seen next-hash) (inc steps))))))
