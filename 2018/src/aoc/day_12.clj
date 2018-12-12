(ns aoc.day-12
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(t/deftest day-12-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-12-b
  (t/testing "input"
    (t/is (= 0 0))))

(defn initial-state [s]
  (map (comp keyword str) (str/replace s #"initial state: " "")))

(defn rule-fns [rules]
  (keep
    (fn [s]
      (let [[ll l c r rr n] (map (comp keyword str) (str/replace s #" => " ""))]
        (if (= c n)
          nth
          (fn [pots n]
            (if (and (= (nth pots n) c)

                     (= (nth pots (- n 2)) ll)
                     (= (nth pots (- n 1)) l)

                     (= (nth pots (+ n 1)) r)
                     (= (nth pots (+ n 2)) rr))
              n
              c)))))
    rules))

(aoc/with-input "day-12"
  (fn [f]
    (let [[state _ & rules] (str/split-lines (slurp f))]
      {:state (initial-state state)
       :rule-fns (rule-fns rules)})))
