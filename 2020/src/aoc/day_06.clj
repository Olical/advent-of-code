(ns aoc.day-06
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [clojure.set :as set]
            [aoc.core :as aoc]))

(def input (aoc/with-input "day-06"
             (fn [reader]
               (map #(map set (str/split-lines %))
                    (str/split (slurp reader) #"\n\n")))))

(defn part-a [groups]
  (->> groups
       (map
         (fn [group]
           (count (reduce into group))))
       (reduce +)))

(defn part-b [groups]
  (->> groups
       (map
         (fn [group]
           (count (apply set/intersection group))))
       (reduce +)))

(t/deftest day-06-a
  (t/is (= 6297 (part-a input))))

(t/deftest day-06-b
  (t/is (= 3158 (part-b input))))
