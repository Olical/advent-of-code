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
         weights {}]
    (if-let [{:keys [children weight name] :as node} (first nodes)]
      (let [child-weights (map weights children)]
        (cond
          (and (seq children) (not (contains? weights name)))
          (recur (concat (map tree children) nodes) (assoc weights name weight))

          (and (contains? weights name) (apply not= child-weights))
          (-> (remove (fn [[value amount]]
                        (= amount 1))
                      (frequencies child-weights))
              keys
              first)

          :else (recur (rest nodes)
                       (assoc weights name (apply + weight child-weights)))))
      [:all-good weights])))
