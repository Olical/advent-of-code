(ns advent.day-18
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def op-strs {"snd" :play-sound
              "set" :assoc-reg
              "add" :+-reg
              "mul" :*-reg
              "mod" :mod-reg
              "rcv" :recover-sound-nz
              "jgz" :jump-gz})

(defn parse [src]
  (->> (str/split (str/trim src) #"\n")
       (into [] (comp (map #(str/split % #"\s"))
                      (map (fn [[op reg value]]
                             {:op (op-strs op)
                              :reg (keyword reg)
                              :value (edn/read-string value)}))))))

(defn get-reg [regs reg]
  (get regs reg 0))

(defn freq [instructions]
  (loop [pointer 0
         regs {}
         last-sound 0]
    (let [{:keys [op reg value]} (get instructions pointer)
          current (get-reg regs reg)]
      (when (< -1 pointer (count instructions))
        (case op
          :play-sound (recur (inc pointer) regs last-sound)
          :assoc-reg (recur (inc pointer) (assoc regs reg value) last-sound)
          :+-reg (recur (inc pointer) (assoc regs reg (+ current value)) last-sound)
          :*-reg (recur (inc pointer) (assoc regs reg (* current value)) last-sound)
          :mod-reg (recur (inc pointer) (assoc regs reg (mod current value)) last-sound)
          :recover-sound-nz (if (not= current 0)
                              last-sound
                              (recur (inc pointer) regs last-sound))
          :jump-gz (if (> current 0)
                     (recur (+ pointer value) regs last-sound)
                     (recur (inc pointer) regs last-sound)))))))
