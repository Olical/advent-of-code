(ns aoc.day-11
  (:require [clojure.test :as t]
            [clojure.string :as str]))

(def grid-serial 9110)

(defn power [x y]
  (let [rack (+ x 10)]
    (-> (* rack y)
        (+ grid-serial)
        (* rack)

        ;; PLEASE FORGIVE ME
        (-> str reverse (nth 2 \0) (str) (Integer/parseInt))

        (- 5))))

(def grid
  (mapv
    vec
    (partition
      300
      (for [x (range 1 301)
            y (range 1 301)]
        (power x y)))))

(defn square-sum [x y size]
  (reduce
    +
    (for [x (range x (+ x size))
          y (range y (+ y size))]
      (get-in grid [(dec x) (dec y)]))))

(defn powerful-square []
  (->> (for [x (range 1 299)
             y (range 1 299)]
         {:pos [x y]
          :sum (square-sum x y 3)})
       (sort-by :sum)
       (last)
       :pos
       (str/join ",")))

(defn best-sized-square []
  (->> (for [x (range 1 300)
             y (range 1 300)
             size (range 1 20)
             :when (and (<= (+ x size) 300)
                        (<= (+ y size) 300))]
         {:coord [x y size]
          :power (square-sum x y size)})
       (sort-by :power)
       (last)
       :coord
       (str/join ",")))

(t/deftest day-11-a
  (t/testing "input"
    (t/is (= (time (powerful-square)) "21,13"))))

(t/deftest day-11-b
  (t/testing "input"
    (t/is (= (time (best-sized-square)) "235,268,13"))))
