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

(defn dance [lineup src]
  (loop [programs (str/split lineup #"")
         [move & rest-moves] (parse src)]
    (if move
      (recur (case (:type move)
               :spin (spin programs (:value move))
               :exchange (exchange programs (:value move))
               :partner (partner programs (:value move)))
             rest-moves)
      (str/join "" programs))))
