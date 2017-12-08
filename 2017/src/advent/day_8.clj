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

(defn max-reg [regs]
  (if (empty? regs)
    0
    (apply max (vals regs))))

(defn max-reg-ever [regs]
  (if-let [m (meta regs)]
    (:max-ever m)
    0))

(defn apply-inst [regs {:keys [reg op amount check]}]
  (if (satisfied? regs check)
    (let [next-regs (update regs reg #(op (or % 0) amount))
          new-max (max-reg regs)
          old-max (max-reg-ever regs)]
      (if (> new-max old-max)
        (with-meta next-regs {:max-ever new-max})
        next-regs))
    regs))

(defn execute [insts]
  (reduce apply-inst {} insts))
