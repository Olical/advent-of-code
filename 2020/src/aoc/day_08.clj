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

(defn step [program {:keys [pc acc]}]
  (let [{:keys [op v]} (nth program pc)]
    (case op
      :nop {:pc (inc pc)
            :acc acc}
      :acc {:pc (inc pc)
            :acc (+ acc v)}
      :jmp {:pc (+ pc v)
            :acc acc})))

(defn execute [program]
  (loop [state {:pc 0 :acc 0}
         seen-pcs #{}]
    (let [{:keys [pc] :as state} (step program state)]
      (cond
        (contains? seen-pcs pc)
        {:exit :loop
         :state state}

        (= pc (count program))
        {:exit :complete
         :state state}

        (> pc (count program))
        {:exit :overshot
         :state state}

        :else (recur state (conj seen-pcs pc))))))

(defn exicute-mutations [program]
  (loop [mutation-pc 0]
    (let [program (update program mutation-pc
                          (fn [{:keys [op] :as step}]
                            (assoc step :op
                                   (case op
                                     :jmp :nop
                                     :nop :jmp
                                     op))))
          {:keys [exit] :as result} (execute program)]
      (case exit
        :complete result
        (= mutation-pc (dec (count program))) nil
        (recur (inc mutation-pc))))))

(t/deftest day-08-a
  (t/is (= {:op :acc, :sign :+, :v 1} (parse "acc +1")))
  (t/is (= {:op :nop, :sign :-, :v -20} (parse "nop -20")))
  (t/is (= 1709 (get-in (execute input) [:state :acc]))))

(t/deftest day-08-b
  (t/is (= 0 (get-in (exicute-mutations input) [:state :acc]))))
