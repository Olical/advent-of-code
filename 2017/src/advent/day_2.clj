(ns advent.day-2
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (map #(map edn/read-string (str/split % #"\s+")))))

(defn checksum [src]
  (->> (parse src)
       (map (fn [row]
              (Math/abs (- (apply min row) (apply max row)))))
       (apply +)))
