(ns advent.day-14
  (:require [advent.day-10 :as knot-hash]
            [clojure.pprint :as pprint]))

(defn row [input n]
  (let [hash (knot-hash/knot-hash (str input "-" n))]
    (into [] (comp (map #(read-string (str "0x" %)))
                   (map #(pprint/cl-format nil "~4,'0',B" %))
                   (mapcat (fn [bin-chars]
                             (map (fn [c] {:used? (= \1 c)}) bin-chars))))
          hash)))

(defn memory [input]
  (loop [n 127
         cells []]
    (if (>= n 0)
      (recur (dec n) (concat cells (row input n)))
      cells)))
