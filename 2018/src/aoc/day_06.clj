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
  {:from {:x (->> coords (map :x) (apply min) (- 15))
          :y (->> coords (map :y) (apply min) (- 15))}
   :to {:x (->> coords (map :x) (apply max) (+ 15))
        :y (->> coords (map :y) (apply max) (+ 15))}})

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

(defn edge? [{:keys [x y]}]
  (or (= x (-> bounds :from :x))
      (= y (-> bounds :from :y))

      (= x (-> bounds :to :x))
      (= y (-> bounds :to :y))))

(comment
  (time
    (->> (loop [[cell & cells] cells
                edges {}
                acc {}]
           (if cell
             (let [closest (closest coords cell)]
               (cond
                 (edges closest) (recur cells edges acc)
                 (edge? closest) (recur cells (assoc edges closest true) (dissoc acc closest))
                 :else (recur cells edges (update acc closest (fnil inc 0)))))
             acc))
         (vals)
         (apply max))))

(t/deftest day-06-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-06-b
  (t/testing "input"
    (t/is (= 0 0))))
