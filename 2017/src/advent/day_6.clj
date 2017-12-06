(ns advent.day-6
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\s+")
       (mapv edn/read-string)))

(defn max-index [coll]
  (.indexOf coll (apply max coll)))

(defn redistributions [banks mode]
  (loop [banks banks
         seen #{}
         steps 1]
    (let [index (max-index banks)
          next-banks (loop [blocks (nth banks index)
                            banks (assoc banks index 0)
                            index (inc index)]
                       (if (= 0 blocks)
                         banks
                         (recur (dec blocks)
                                (update banks (mod index (count banks)) inc)
                                (inc index))))
          next-hash (hash next-banks)]
      (if (or (contains? seen next-hash))
        (if (= mode :infinite)
          (dec (redistributions banks :single))
          steps)
        (recur next-banks (conj seen next-hash) (inc steps))))))
