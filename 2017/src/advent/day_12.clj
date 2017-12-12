(ns advent.day-12
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (into {} (comp (map #(str/split % #" <-> "))
                 (map (fn [[from to]]
                        [(edn/read-string from)
                         (map edn/read-string (str/split to #", "))])))
        (str/split src #"\n")))

(defn can-reach? [pipes start target]
  (loop [seen #{}
         work [start]]
    (if (empty? work)
      false
      (let [current (first work)
            possible (set (remove seen (pipes current)))]
        (if (contains? possible target)
          true
          (recur (conj seen current) (concat (rest work) possible)))))))

(defn group [pipes target]
  (set (conj (filter #(can-reach? pipes % target) (keys pipes)) target)))

(defn seen-in-group? [groups target]
  (boolean (first (filter #(contains? % target) groups))))

(defn groups [pipes]
  (reduce (fn [acc target]
            (if (seen-in-group? acc target)
              acc
              (conj acc (group pipes target))))
          #{}
          (keys pipes)))
