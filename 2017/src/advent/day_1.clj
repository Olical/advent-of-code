(ns advent.day-1
  (:require [clojure.edn :as edn]
            [clojure.string :as str]))

(defn parse [input]
  (let [result (->> (str/split input #"")
                    (remove #{"\n"})
                    (map edn/read-string))]
    (conj result (last result))))

(defn sum-pairs [input]
  (loop [digits (parse input)
         prev nil
         acc 0]
    (let [digit (first digits)]
      (if digit
        (recur (rest digits)
               digit
               (if (= digit prev)
                 (+ acc prev)
                 acc))
        acc))))
