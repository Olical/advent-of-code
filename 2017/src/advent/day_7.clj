(ns advent.day-7
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #"\n")
       (into []
             (comp (map #(str/split % #",?\s"))
                   (map (fn [[name weight _ & children]]
                          (merge {:name name
                                  :weight (edn/read-string (re-find #"\d+" weight))}
                                 (when children
                                   {:children (set children)}))))))))

(defn parent [program tree]
  (first (filter (fn [{:keys [children]}]
                   (contains? children program))
                 tree)))

(defn root [tree]
  (first (remove #(parent % tree) (map :name tree))))

(defn fix-weight [tree]
  (let [leaves (group-by #(parent (:name %) tree) (remove :children tree))]
    leaves))
