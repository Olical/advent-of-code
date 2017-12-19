(ns advent.day-15
  (:require [clojure.pprint :as pprint]))

(defn last-16-bits [n]
  (let [bits (Integer/toString n 2)]
    (drop (- (count bits) 16) bits)))

(defn judge [{:keys [a b]}]
  (loop [a a
         b b
         score 0
         iterations 40000000]
    (if (>= iterations 0)
      (let [a (rem (* a 16807) 2147483647)
            b (rem (* b 48271) 2147483647)
            a-bin (last-16-bits a)
            b-bin (last-16-bits b)]
        (recur a
               b
               (if (= a-bin b-bin)
                 (inc score)
                 score)
               (dec iterations)))
      score)))

(defn gen-next [n factor multiple]
  (loop [n n]
    (let [n (rem (* n factor) 2147483647)]
      (if (= 0 (mod n multiple))
        n
        (recur n)))))

(defn picky-judge [{:keys [a b]}]
  (loop [a a
         b b
         score 0
         iterations 5000000]
    (if (>= iterations 0)
      (let [a (gen-next a 16807 4)
            b (gen-next b 48271 8)
            a-bin (last-16-bits a)
            b-bin (last-16-bits b)]
        (recur a
               b
               (if (= a-bin b-bin)
                 (inc score)
                 score)
               (dec iterations)))
      score)))
