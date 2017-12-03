(ns advent.day-3)

(def neighbour-coords #{{:x 1} {:y -1} {:x -1} {:y 1}
                       {:x 1, :y -1} {:x -1, :y -1}
                       {:x -1, :y 1} {:x 1, :y 1}})

(defn neighbour-sum [grid coord]
  (let [rel-neighbours (map #(merge-with + coord %) neighbour-coords)
        neighbour-vals (vals (select-keys grid rel-neighbours))]
    (if (empty? neighbour-vals)
      1M
      (apply + neighbour-vals))))

(defn spiral [target strategy]
  (loop [coord {:x 0, :y 0}
         n 1
         run-length 0
         turn-length (interleave (rest (range)) (rest (range)))
         dirs (cycle [{:x 1} {:y -1} {:x -1} {:y 1}])
         sum-grid {}]
    (let [sum-grid (assoc sum-grid coord (neighbour-sum sum-grid coord))]
      (cond
        (and (= strategy :distance) (= n target)) (+ (Math/abs (:x coord)) (Math/abs (:y coord)))
        (and (= strategy :sum-gt) (>= (sum-grid coord) target)) (sum-grid coord)
        (= run-length
           (first turn-length)) (recur coord
           n
           0
           (rest turn-length)
           (rest dirs)
           sum-grid)
        :else (recur (merge-with + coord (first dirs))
                     (inc n)
                     (inc run-length)
                     turn-length
                     dirs
                     sum-grid)))))
