(ns aoc.day-10
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(t/deftest day-10-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-10-b
  (t/testing "input"
    (t/is (= 0 0))))

(def point-re #"position=<(.+), (.+)> velocity=<(.+), (.+)>")

(defn parse-point [s]
  (let [[px py vx vy]
        (->> (re-matches point-re s)
             (rest)
             (map edn/read-string))]
    {:pos {:x px, :y py}
     :vel {:x vx, :y vy}}))

(defn advance [points steps]
  (mapv
    (fn [point]
      (-> point
          (update-in [:pos :x] + (* steps (get-in point [:vel :x])))
          (update-in [:pos :y] + (* steps (get-in point [:vel :y])))))
    points))

(def points
  (aoc/with-lines "day-10" parse-point vec))

(defn lines? [points]
  (loop [[{:keys [x y]} & points]
         (->> (map :pos points)
              (sort-by (juxt :x :y)))
         px nil
         py nil
         streak 0]
    (cond
      (> streak 3)
      true

      (not (and x y))
      false

      :else (recur points x y
                   (if (and (= x px) (= y (inc py)))
                     (inc streak)
                     0)))))

(defn find-lines []
  (loop [step 0]
    (let [points (advance points step)]
      (cond
        (lines? points) {:step step
                         :points points}
        :else (recur (inc step))))))

(let [points (advance points 10942)

      first-last (juxt first last)
      [small-x large-x] (->> points (map (comp :x :pos)) (sort) (first-last))
      [small-y large-y] (->> points (map (comp :y :pos)) (sort) (first-last))]
  (->> points
           (map :pos)
           (map (fn [pos]
                  (-> pos
                      (update :x - small-x)
                      (update :y - small-y))))
           (sort-by (juxt :x :y))
           (group-by :y)
           (sort-by key)
           (map val)
           (map (fn [points]
                  (reduce
                    (fn [acc pos]
                      (assoc acc (:x pos) "#"))
                    (vec (repeat large-x " "))
                    points)))
           (map str/join)
           (str/join "\n")
           (println)))
