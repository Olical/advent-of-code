(ns aoc.day-13
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(t/deftest day-13-a
  (t/testing "input"
    (t/is (= 0 0))))

(t/deftest day-13-b
  (t/testing "input"
    (t/is (= 0 0))))

(defn char->tile [c]
  (case c
    \| [:track :vertical]
    \- [:track :horizontal]
    \/ [:track :BL->TR]
    \\ [:track :TL->BR]
    \+ [:track :intersection]
    \> [:cart :right 0]
    \v [:cart :down 0]
    \< [:cart :left 0]
    \^ [:cart :up 0]
    [:space]))

(def grid
  (aoc/with-lines "day-13" #(mapv char->tile %) vec))
