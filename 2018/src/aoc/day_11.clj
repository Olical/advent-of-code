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
  (partition
    300
    (for [x (range 1 301)
          y (range 1 301)]
      (power x y))))

(def cache! (atom {}))

(defn square-sum [x y size]
  (if-let [cached (get @cache! [x y size])]
    cached
    (let [xr (range x (+ x size))
          yr (range y (+ y size))
          result (reduce
                   +
                   (for [x xr
                         y yr]
                     (-> grid
                         (nth x)
                         (nth y))))]
      (swap!
        cache!
        (fn [cache]
          (into
            cache
            (for [x xr
                  y yr]
              [[x y size] result]))))
      result)))

(defn powerful-square []
  (->> (for [x (range 1 299)
             y (range 1 299)]
         {:pos [x y]
          :sum (square-sum (dec x) (dec y) 3)})
       (sort-by :sum)
       (last)
       :pos
       (str/join ",")))

(t/deftest day-11-a
  (t/testing "input"
    (t/is (= (time (powerful-square)) "21,13"))))

(t/deftest day-11-b
  (t/testing "input"
    (t/is (= 0 0))))
