(ns advent.day-7
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (into {}
             (comp (map #(str/split % #",?\s"))
                   (map (fn [[name weight _ & children]]
                          [name (merge {:name name
                                        :weight (edn/read-string (re-find #"\d+" weight))}
                                       (when children
                                         {:children (set children)}))]))))))

(defn parent [program nodes]
  (first (filter (fn [{:keys [children]}]
                   (contains? children program))
                 nodes)))

(defn root [tree]
  (let [nodes (vals tree)]
    (first (remove #(parent (:name %) nodes) nodes))))

(defn bad-weight [tree]
  (loop [nodes [(root tree)]
         weights {}]))
