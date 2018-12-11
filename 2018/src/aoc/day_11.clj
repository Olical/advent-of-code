(ns aoc.day-11
  (:require [clojure.test :as t]
            [clojure.string :as str]))

(defn power [grid-serial x y]
  (let [rack (+ x 10)]
    (-> (* rack y)
        (+ grid-serial)
        (* rack)

        ;; PLEASE FORGIVE ME
        (-> str reverse (nth 2 \0) (str) (Integer/parseInt))

        (- 5))))

(def grid-serial 9110)

(def grid
  (partition
    300
    (for [x (range 1 301)
          y (range 1 301)]
      (power grid-serial x y))))

(defn square-sum [grid x y size]
  (reduce
    +
    (for [x (range x (+ x size))
          y (range y (+ y size))]
      (-> grid
          (nth x)
          (nth y)))))

(defn powerful-square []
  (->> (for [x (range 1 299)
             y (range 1 299)]
         {:pos [x y]
          :sum (square-sum grid (dec x) (dec y) 3)})
       (sort-by :sum)
       (last)
       :pos
       (str/join ",")))

(t/deftest day-11-a
  (t/testing "input"
    (t/is (= (powerful-square) "21,13"))))

(t/deftest day-11-b
  (t/testing "input"
    (t/is (= 0 0))))
