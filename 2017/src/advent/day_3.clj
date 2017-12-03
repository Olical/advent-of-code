(ns advent.day-3)

(defn distance [target]
  (loop [coord {:x 0, :y 0}
         n 1
         run-length 0
         turn-length (interleave (rest (range)) (rest (range)))
         dirs (cycle [{:x 1} {:y -1} {:x -1} {:y 1}])]
    (cond
      (= n target) (+ (Math/abs (:x coord)) (Math/abs (:y coord)))
      (= run-length
         (first turn-length)) (recur coord
                                     n
                                     0
                                     (rest turn-length)
                                     (rest dirs))
      :else (recur (merge-with + coord (first dirs))
                   (inc n)
                   (inc run-length)
                   turn-length
                   dirs))))
