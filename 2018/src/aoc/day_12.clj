(ns aoc.day-12
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(defn initial-state [s]
  (zipmap
    (range)
    (map (comp keyword str) (str/replace s #"initial state: " ""))))

(defn rule-fns [rules]
  (keep
    (fn [s]
      (let [[ll l cur r rr res] (map (comp keyword str) (str/replace s #" => " ""))]
        (when (not= res :.)
          (fn [pots n]
            (when (and (= (get pots n :.) cur)

                       (= (get pots (- n 2) :.) ll)
                       (= (get pots (- n 1) :.) l)

                       (= (get pots (+ n 1) :.) r)
                       (= (get pots (+ n 2) :.) rr))
              res)))))
    rules))

(def input
  (aoc/with-input "day-12"
    (fn [f]
      (let [[state _ & rules] (str/split-lines (slurp f))]
        {:state (initial-state state)
         :rule-fns (rule-fns rules)}))))

(defn step [state]
  (into
    {}
    (keep
      (fn [n]
        (let [res (or (some
                        (fn [f]
                          (f state n))
                        (:rule-fns input))
                      :.)]
          (when-not (and (= res :.) (not (contains? state n)))
            [n res]))))
    (range (- (apply min (keys state)) 2) (+ (apply max (keys state)) 2))))

(defn sum-state [state]
  (->> state
       (filter #(= (val %) :#))
       (map key)
       (reduce +)))

(defn gen-20 []
  (->> (reduce
         (fn [state _]
           (step state))
         (:state input)
         (range 20))
       (sum-state)))

(defn gen-50000000000 []
  (->> (reductions
         (fn [state _]
           (step state))
         (:state input)
         (range 200))
       (map sum-state)
       (last)
       (+ (* 86 (- 50000000000 200)))))

(t/deftest day-12-a
  (t/testing "input"
    (t/is (= (gen-20) 3793))))

(t/deftest day-12-b
  (t/testing "input"
    (t/is (= (gen-50000000000) 4300000002414))))
