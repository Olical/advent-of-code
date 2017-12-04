(ns advent.day-4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse [src]
  (->> (str/split src #"\n")
       (map #(str/split % #"\s+"))))

(defn valid-phrase-count [src]
  (->> (parse src)
       (filter #(apply distinct? %))
       (count)))
