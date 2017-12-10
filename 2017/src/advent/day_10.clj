(ns advent.day-10
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

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

(defn knot [lengths mem pos skip]
  (let [size (count mem)]
    (loop [lengths lengths
           mem mem
           pos pos
           skip skip]
      (if-let [[length & rest-lengths] lengths]
        (recur rest-lengths
               (ring-reverse mem pos length)
               (mod (+ pos length skip) size)
               (inc skip))
        {:mem mem
         :pos pos
         :skip skip}))))

(defn twist [src size]
  (->> (knot (map edn/read-string (str/split src #",")) (vec (range size)) 0 0)
       (:mem)
       (take 2)
       (apply *)))

(defn knot-hash [src]
  (let [lengths (concat (map int (str/trim src)) (list 17 31 73 47 23))
        sparse-hash (loop [rounds 64
                           {:keys [pos skip mem]} {:mem (vec (range 256))
                                                   :pos 0
                                                   :skip 0}]
                      (if (zero? rounds)
                        mem
                        (recur (dec rounds) (knot lengths mem pos skip))))]
    (->> (map #(format "%02x" (apply bit-xor %))
              (partition 16 sparse-hash))
         (str/join))))
