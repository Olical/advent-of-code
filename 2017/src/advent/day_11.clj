(ns advent.day-11
  (:require [clojure.string :as str]))

(def dirs {"n" :north
           "s" :south
           "ne" :northeast
           "se" :southeast
           "sw" :southwest
           "nw" :northwest})

(def dir->pos {:north {:y -1}
               :south {:y 1}
               :northeast {:x 1, :y -1}
               :southeast {:x 1, :y 1}
               :southwest {:x -1, :y 1}
               :northwest {:x -1, :y -1}})

(defn parse [src]
  (->> (str/split (str/trim src) #",")
       (map dirs)))

(defn hex-distance [{aq :x, ar :y} {bq :x, br :y}]
  (/ (+ (Math/abs (- aq bq))
        (Math/abs (+ aq (- ar bq br)))
        (Math/abs (- ar br)))
     2))

(defn distance [src]
  (let [pos (reduce (fn [pos dir]
                      (merge-with + pos (dir->pos dir)))
                    {:x 0, :y 0}
                    (parse src))]
    (hex-distance {:x 0, :y 0} pos)))
