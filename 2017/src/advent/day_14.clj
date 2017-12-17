(ns advent.day-14
  (:require [advent.day-10 :as knot-hash]
            [clojure.pprint :as pprint]))

(defn row [input y]
  (let [hash (knot-hash/knot-hash (str input "-" y))]
    (into [] (comp (map #(read-string (str "0x" %)))
                   (map #(pprint/cl-format nil "~4,'0',B" %))
                   (mapcat (fn [bin-chars]
                             (map-indexed (fn [x c] {:used? (= \1 c)
                                                     :pos {:x x, :y y}})
                                          bin-chars))))
          hash)))

(defn memory [input]
  (loop [n 127
         cells []]
    (if (>= n 0)
      (recur (dec n) (concat (row input n) cells))
      cells)))

(defn xy->n [{:keys [x y]}]
  (+ x (* y 128)))

(defn neighbours [cells pos]
  (->> #{{:x -1} {:y -1} {:x 1} {:y 1}}
       (map #(get cells (xy->n (merge-with + pos %))))
       (remove #(= false (:used? %)))))

(defn regions [input]
  (let [cells (vec (memory input))]
    (->> (filter :used? cells)
         (reduce (fn [cells cell]
                   (let [region (or (some :region (neighbours cells (:pos cell)))
                                    (gensym "region"))]
                     (update cells (xy->n (:pos cell)) assoc :region region)))
                 cells)
         (group-by :region))))
