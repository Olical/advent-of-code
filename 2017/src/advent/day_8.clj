(ns advent.day-8
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def ops {"inc" +
          "dec" -})

(def preds {">" >
            "<" <
            ">=" >=
            "==" =
            "<=" <=
            "!=" not=})

(defn parse [src]
  (->> (str/split src #"\n")
       (into [] (comp (map #(str/split % #"\s"))
                      (map (fn [[reg op amount _ check-reg check-pred check-value]]
                             {:reg (keyword reg)
                              :op (ops op)
                              :amount (edn/read-string amount)
                              :check {:reg (keyword check-reg)
                                      :pred (preds check-pred)
                                      :value (edn/read-string check-value)}}))))))

(defn satisfied? [regs {:keys [reg pred value]}]
  (pred (get regs reg 0) value))

(defn apply-inst [regs {:keys [reg op amount check]}]
  (if (satisfied? regs check)
    (update regs reg #(op (or % 0) amount))
    regs))

(defn execute [insts]
  (reduce apply-inst {} insts))

(defn max-reg [regs]
  (apply max (vals regs)))
