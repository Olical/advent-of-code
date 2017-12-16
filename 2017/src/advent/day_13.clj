(ns advent.day-13
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split (str/trim src) #"\n")
       (into {}
             (comp
              (map #(str/split % #": "))
              (map (fn [[depth width]]
                     [(edn/read-string depth)
                      (edn/read-string width)]))))))

(defn severity [firewall]
  (let [last-depth (apply max (keys firewall))]
    (loop [depth 0
           severity 0
           ranges (into {}
                        (map (fn [[depth width]]
                               [depth (cycle (concat (range width)
                                                     (rest (butlast (reverse (range width))))))])
                             firewall))]
      (if (> depth last-depth)
        severity
        (recur (inc depth)
               (if (= 0 (first (get ranges depth)))
                 (+ severity (* depth (get firewall depth)))
                 severity)
               (into {}
                     (map (fn [[depth width]]
                            [depth (rest width)])
                          ranges)))))))
