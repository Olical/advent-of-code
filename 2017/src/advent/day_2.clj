(ns advent.day-2
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (map #(map edn/read-string (str/split % #"\s+")))))

(defn row-min-max [row]
  (Math/abs (- (apply min row) (apply max row))))

(defn row-divisible [row]
  (first
   (filter integer?
           (for [x row
                 y row
                 :when (not= x y)]
             (/ x y)))))

(defn checksum [src row-fn]
  (->> (parse src)
       (map row-fn)
       (apply +)))
