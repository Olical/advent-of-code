(ns aoc.day-03
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn parse-row [s]
  (cycle (map {\. :., \# :t} s)))

(def input (aoc/with-lines "day-03" parse-row vec))

(defn path [{:keys [world right down]}]
  (loop [world world
         acc []]
    (let [world (->> world
                     (drop down)
                     (mapv #(drop right %)))
          current-pos (first (first world))]
      (if current-pos
        (recur world (conj acc current-pos))
        acc))))

(t/deftest day-03-a
  (t/is (= [:. :t :t :. :t
            :. :t :t]
           (vec (take 8 (parse-row ".##.#")))))
  (t/is (= 237 (count (filter #{:t} (path {:world input, :right 3, :down 1}))))))

(t/deftest day-03-b
  (t/is (= 2106818610
           (->> [{:right 1, :down 1}
                 {:right 3, :down 1}
                 {:right 5, :down 1}
                 {:right 7, :down 1}
                 {:right 1, :down 2}]
                (map
                  (fn [step]
                    (count (filter #{:t} (path (assoc step :world input))))))
                (reduce *)))))
