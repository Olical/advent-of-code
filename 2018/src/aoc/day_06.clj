(ns aoc.day-06
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(defn parse-coord [s]
  (let [[x y] (->> (str/split s #", ") (map #(Integer/parseInt %)))]
    {:x x
     :y y}))

(def coords
  (aoc/with-lines "day-06" parse-coord vec))

(def bounds
  {:from {:x (->> coords (map :x) (apply min) (- 20))
          :y (->> coords (map :y) (apply min) (- 20))}
   :to {:x (->> coords (map :x) (apply max) (+ 20))
        :y (->> coords (map :y) (apply max) (+ 20))}})

(def cells
  (for [x (range (-> bounds :from :x) (-> bounds :to :x inc))
        y (range (-> bounds :from :y) (-> bounds :to :y inc))]
    {:x x
     :y y}))

(defn closest [coords {tx :x, ty :y}]
  (let [[{:keys [coord distance]} & others]
        (->> coords
             (map
               (fn [{:keys [x y] :as coord}]
                 {:coord coord
                  :distance (+ (aoc/abs (- x tx))
                               (aoc/abs (- y ty)))}))
             (sort-by :distance))]
    (if (some #(= distance (:distance %)) others)
      :ambiguous
      coord)))

(comment
  (time
    (loop [[cell & cells] cells
           acc {}]
      (if cell
        (recur cells (update acc (closest coords cell) (fnil inc 0)))
        acc))))

(t/deftest day-06-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-06-b
  (t/testing "input"
    (t/is (= 0 0))))
