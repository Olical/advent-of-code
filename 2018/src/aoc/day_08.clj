(ns aoc.day-08
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(def input
  (aoc/with-lines "day-08" identity
    (fn [lines]
      (->> (str/split (first lines) #"\s")
           (map #(Integer/parseInt %))))))

(do
  (defn parse [input]
    (let [[cc mc & input] input]
      ))
  (parse input))

(t/deftest day-08-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-08-b
  (t/testing "input"
    (t/is (= 0 0))))
