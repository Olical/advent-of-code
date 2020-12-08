(ns aoc.day-08
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn parse [s]
  (let [[_ op sign v] (re-matches #"(\w{3}) ([+-])(\d+)" s)
        sign (keyword sign)
        v (Integer/parseInt v)]
    {:op (keyword op)
     :sign sign
     :v (case sign
          :+ v
          :- (- v))}))

(def input (aoc/with-lines "day-08" parse vec))

(defn step [{:keys [pc acc]}]
  (let [{:keys [op v]} (nth input pc)]
    (case op
      :nop {:pc (inc pc)
            :acc acc}
      :acc {:pc (inc pc)
            :acc (+ acc v)}
      :jmp {:pc (+ pc v)
            :acc acc})))

(defn until-loop []
  (loop [state {:pc 0 :acc 0}
         states [{:pc 0 :acc 0}]
         seen-pcs #{}]
    (let [{:keys [pc] :as state} (step state)]
      (if (contains? seen-pcs pc)
        states
        (recur state (conj states state) (conj seen-pcs pc))))))

(t/deftest day-08-a
  (t/is (= {:op :acc, :sign :+, :v 1} (parse "acc +1")))
  (t/is (= {:op :nop, :sign :-, :v -20} (parse "nop -20")))
  (t/is (= 1709 (:acc (last (until-loop))))))

(t/deftest day-08-b
  (t/is (= 0 0)))
