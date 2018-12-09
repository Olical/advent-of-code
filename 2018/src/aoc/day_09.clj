(ns aoc.day-09
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [aoc.core :as aoc]))

(def input
  (aoc/with-lines "day-09" edn/read-string first))

(defn score? [n]
  (zero? (mod n 23)))

(defn -m [coll pos]
  (concat (take pos coll) (drop (inc pos) coll)))

(defn +m [coll pos v]
  (let [[before after] (split-at pos coll)]
    (concat before [v] after)))

(t/deftest day-09-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-09-b
  (t/testing "input"
    (t/is (= 0 0))))

(time
  (loop [circle '(0)
         current 0
         marbles (vec (range 1 (inc (:last input))))
         [player & players] (cycle (range (:players input)))
         scores {}]
    (if (empty? marbles)
      (->> scores (sort-by val) (last) (val))
      (let [[marble & marbles] marbles]
        (if (score? marble)
          (let [to-remove (mod (- current 7) (count circle))]
            (recur (-m circle to-remove)
                   to-remove
                   marbles
                   players
                   (update scores player (fnil + 0)
                           marble (nth circle to-remove))))
          (let [current (mod (+ current 2) (count circle))]
            (recur (+m circle current marble)
                   current
                   marbles
                   players
                   scores)))))))

;; 37305?
