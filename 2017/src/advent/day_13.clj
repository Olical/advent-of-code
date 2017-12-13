(ns advent.day-13
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split (str/trim src) #"\n")
       (into {}
             (comp
              (map #(str/split % #": "))
              (map (fn [[depth range]]
                     [(edn/read-string depth)
                      (edn/read-string range)]))))))

(defn severity [firewall]
  nil)
