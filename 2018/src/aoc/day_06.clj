(ns aoc.day-06
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(defn parse-coord [s]
  (let [[x y] (->> (str/split s #", ") (map #(Integer/parseInt %)))]
    {:x x
     :y y}))

(defonce coords
  (aoc/with-lines "day-06" parse-coord vec))

(t/deftest day-06-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-06-b
  (t/testing "input"
    (t/is (= 0 0))))
