(ns advent.day-7
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (into []
             (comp (map #(str/split % #",?\s"))
                   (map (fn [[name weight _ & children]]
                          {:name name
                           :weight (edn/read-string (re-find #"\d+" weight))
                           :children (set children)}))))))

(defn parent [program tree]
  (first (filter
          (fn [{:keys [children]}]
            (contains? children program))
          tree)))

(defn bottom-program [tree]
  (first (remove #(parent % tree) (map :name tree))))
