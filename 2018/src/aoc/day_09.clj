(ns aoc.day-09
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [aoc.core :as aoc]))

(def input
  (aoc/with-lines "day-09" edn/read-string first))

(defn score? [n]
  (zero? (mod n 23)))

(defn node [prev value next]
  {:prev (atom prev)
   :value value
   :next (atom next)})

(defn init [v]
  (let [temp (node nil v nil)]
    (reset! (:prev temp) temp)
    (reset! (:next temp) temp)))

(defn remove-node [node]
  (let [next @(:next node)
        prev @(:prev node)]
    (reset! (:next prev) next)
    (reset! (:prev next) prev)
    next))

(defn insert-node [prev value]
  (let [next @(:next prev)
        new-node (node prev value next)]
    (reset! (:next prev) new-node)
    (reset! (:prev next) new-node)))

;; a b
;; a X c

(defn walk+ [node n]
  (if (= n 0)
    node
    (recur @(:next node) (dec n))))

(defn walk- [node n]
  (if (= n 0)
    node
    (recur @(:prev node) (dec n))))

(defn high-score [input]
  (loop [circle (init 0)
         marbles (vec (range 1 (inc (:last input))))
         [player & players] (cycle (range (:players input)))
         scores {}]
    (if (empty? marbles)
      (->> scores (sort-by val) (last) (val))
      (let [[marble & marbles] marbles]
        (if (score? marble)
          (let [to-remove (walk- circle 7)]
            (recur (remove-node to-remove)
                   marbles
                   players
                   (update scores player (fnil + 0)
                           marble (:value to-remove))))
          (recur (insert-node (walk+ circle 1) marble)
                 marbles
                 players
                 scores))))))

(t/deftest day-09-a
  (t/testing "input"
    (t/is (= (high-score input) 408679))))

(t/deftest day-09-b
  (t/testing "input"
    (t/is (= (high-score (update input :last * 100)) 3443939356))))

