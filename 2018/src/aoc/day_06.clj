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
  {:from {:x (->> coords (map :x) (apply min))
          :y (->> coords (map :y) (apply min))}
   :to {:x (->> coords (map :x) (apply max))
        :y (->> coords (map :y) (apply max))}})

(def cells
  (for [x (range (-> bounds :from :x) (-> bounds :to :x inc))
        y (range (-> bounds :from :y) (-> bounds :to :y inc))]
    {:x x
     :y y}))

(defn distance [a b]
  (+ (aoc/abs (- (:x a) (:x b)))
     (aoc/abs (- (:y a) (:y b)))))

(defn closest [coords a]
  (let [[x y]
        (->> coords
             (map
               (fn [b]
                 {:coord b
                  :distance (distance a b)}))
             (sort-by :distance)
             (take 2))]
    (if (= (:distance x) (:distance y))
      :ambiguous
      (:coord x))))

(defn edge? [{:keys [x y]}]
  (or (= x (-> bounds :from :x))
      (= y (-> bounds :from :y))

      (= x (-> bounds :to :x))
      (= y (-> bounds :to :y))))

(defn biggest-island []
  (->> (loop [[cell & cells] cells
              edges {}
              acc {}]
         (if cell
           (let [closest (closest coords cell)]
             (cond
               (= closest :ambiguous) (recur cells edges acc)
               (edges closest) (recur cells edges acc)
               (edge? cell) (recur cells (assoc edges closest true) (dissoc acc closest))
               :else (recur cells edges (update acc closest (fnil inc 0)))))
           acc))
       (vals)
       (apply max)))

(def safe-sum 10000)

(defn distances-sum [coords a]
  (reduce
    (fn [acc b]
      (+ acc (distance a b)))
    0
    coords))

(defn safe-island []
  (loop [[cell & cells] cells
         acc 0]
    (if cell
      (recur cells
             (if (< (distances-sum coords cell) safe-sum)
               (inc acc)
               acc))
      acc)))

(t/deftest day-06-a
  (t/testing "input"
    (t/is (= (biggest-island) 3890))))

(t/deftest day-06-b
  (t/testing "input"
    (t/is (= (safe-island) 40284))))
