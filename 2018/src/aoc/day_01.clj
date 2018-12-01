(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [clojure.edn :as edn]))

(def input (slurp "inputs/day-01.txt"))

(defn sum-lines [input]
  (-> input
      (str/split #"\n")
      (->> (map edn/read-string)
           (reduce +))))

(t/deftest tests
  (t/testing "01 A"
    (t/is (= (sum-lines input) 510)))

  #_(t/testing "01 B"
      (t/is (= (sum-lines input) 0))))
