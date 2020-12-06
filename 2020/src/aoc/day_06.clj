(ns aoc.day-06
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(def input (aoc/with-input "day-06"
             (fn [reader]
               (map str/split-lines (str/split (slurp reader) #"\n\n")))))

(defn part-a [groups]
  (->> groups
       (map
         (fn [group]
           (count (reduce into (map set group)))))
       (reduce +)))

(t/deftest day-06-a
  (t/is (= 0 (part-a input))))

(t/deftest day-06-b
  (t/is (= 0 0)))
