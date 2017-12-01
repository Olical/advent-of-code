(ns advent.day-1
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

(defn parse [input]
  (into []
        (comp (remove #{"\n"})
              (map edn/read-string))
        (str/split input #"")))

(defn sum-pairs [src relation]
  (let [input (parse src)
        input-count (count input)
        input-half (/ input-count 2)]
    (reduce-kv (fn [acc n a]
                 (let [rel-n (case relation
                               :next (inc n)
                               :halfway (+ n input-half))
                       b (nth input (mod rel-n input-count))]
                   (if (= a b)
                     (+ acc a)
                     acc)))
               0
               input)))
