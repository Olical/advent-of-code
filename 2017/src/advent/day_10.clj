(ns advent.day-10
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(defn parse [src]
  (map edn/read-string (str/split src #",")))

(defn ring-reverse [original from length]
  (let [list-size (count original)
        wrap #(mod % list-size)]
    (loop [result original
           pos from
           remaining length]
      (if (zero? remaining)
        result
        (recur (assoc result (wrap pos) (get original (wrap (+ from (dec remaining)))))
               (inc pos)
               (dec remaining))))))

(defn twist-hash [src size]
  (loop [lengths (parse src)
         mem (vec (range size))
         pos 0
         skip 0]
    (if-let [[length & rest-lengths] lengths]
      (recur rest-lengths
             (ring-reverse mem pos length)
             (mod (+ pos length skip) size)
             (inc skip))
      (* (first mem) (second mem)))))
