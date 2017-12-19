(ns advent.day-16
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (->> (str/split src #",")
       (map (fn [[move & others]]
              (let [params (apply str others)
                    pair (str/split params #"/")]
                (case move
                  \s {:type :spin
                      :value (edn/read-string params)}
                  \x {:type :exchange
                      :value (mapv edn/read-string pair)}
                  \p {:type :partner
                      :value pair}))))))

(defn spin [programs n]
  (vec (reverse (take (count programs) (drop n (cycle (reverse programs)))))))

(defn exchange [programs [a b]]
  (-> programs
      (assoc a (get programs b))
      (assoc b (get programs a))))

(defn partner [programs [a b]]
  (exchange programs [(.indexOf programs a) (.indexOf programs b)]))

(defn dance-iter [programs [move & rest-moves]]
  (if move
    (recur (case (:type move)
             :spin (spin programs (:value move))
             :exchange (exchange programs (:value move))
             :partner (partner programs (:value move)))
           rest-moves)
    programs))

(defn dance [lineup src]
  (str/join "" (dance-iter (str/split lineup #"") (parse src))))

(defn dance-a-shit-load [lineup src]
  (let [moves (parse src)
        iters 1000000000]
    (loop [n 0
           programs (str/split lineup #"")
           seen (list programs)
           seen-hashes #{(hash programs)}]
      (if (< n iters)
        (let [step (dance-iter programs moves)
              step-hash (hash step)
              half-size (/ (count seen-hashes) 2)]
          (if (= (take half-size seen-hashes) (drop half-size seen-hashes))
            (str/join "" (nth seen (mod 1000000000 n)))
            (recur (inc n) step (conj seen step) (conj seen-hashes step-hash))))
        (str/join "" programs)))))
