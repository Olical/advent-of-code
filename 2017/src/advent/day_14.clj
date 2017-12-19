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
  (vec
   (loop [n 127
          cells (list)]
     (if (>= n 0)
       (recur (dec n) (concat (row input n) cells))
       cells))))

(defn xy->n [{:keys [x y]}]
  (+ x (* y 128)))

(do
 (defn spreadable [cells pos]
   (->> [{:x -1} {:y -1} {:x 1} {:y 1}]
        (into [] (comp (map #(get cells (xy->n (merge-with + pos %))))
                       (remove nil?)
                       (filter :used?)
                       (remove :region)
                       (map :pos)))))

 (defn spread-region [cells region start-pos]
   (loop [cells cells
          [pos & rest-position] [start-pos]]
     (if pos
       (recur (update cells (xy->n pos) assoc :region region)
              (concat rest-position (spreadable cells pos)))
       cells)))

 (defn regions [cells]
   (loop [cells cells
          region 0
          [pos & rest-positions] (into [] (comp (filter :used?) (map :pos)) cells)]
     (if pos
       (let [cell (get cells (xy->n pos))]
         (if (:region cell)
           (recur cells region rest-positions)
           (recur (spread-region cells region pos) (inc region) rest-positions)))
       (group-by :region (filter :used? cells)))))

 #_(def example-cells (memory "flqrgnkx"))
 #_(count (keys (regions example-cells))))
