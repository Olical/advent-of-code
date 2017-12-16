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

(defn make-ranges [firewall]
  (into {}
        (map (fn [[depth width]]
               [depth (cycle (concat (range width)
                                     (rest (butlast (reverse (range width))))))])
             firewall)))

(defn step-ranges [ranges]
  (into {}
        (map (fn [[depth width]]
               [depth (rest width)])
             ranges)))

(defn severity [firewall {:keys [bail? ranges]}]
  (let [last-depth (apply max (keys firewall))]
    (loop [depth 0
           severity 0
           ranges (or ranges (make-ranges firewall))
           detected? false]
      (if (or (> depth last-depth) (and bail? detected?))
        {:detected? detected?
         :severity severity}
        (let [just-detected? (= 0 (first (get ranges depth)))]
          (recur (inc depth)
                 (if just-detected?
                   (+ severity (* depth (get firewall depth)))
                   severity)
                 (step-ranges ranges)
                 (or detected? just-detected?)))))))

(defn stealth-delay [firewall]
  (loop [n 0
         ranges (make-ranges firewall)]
    (when (= 0 (mod n 1000))
      (prn n))
    (if (:detected? (severity firewall {:bail? true, :ranges ranges}))
      (recur (inc n) (step-ranges ranges))
      n)))
