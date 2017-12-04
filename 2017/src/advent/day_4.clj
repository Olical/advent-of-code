(ns advent.day-4
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn parse [src word-fn]
  (->> (str/split src #"\n")
       (map (fn [phrase]
              (map word-fn (str/split phrase #"\s+"))))))

(defn valid-phrase-count [src word-fn]
  (->> (parse src word-fn)
       (filter #(apply distinct? %))
       (count)))
