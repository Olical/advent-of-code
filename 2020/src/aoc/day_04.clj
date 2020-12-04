(ns aoc.day-04
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [clojure.set :as set]
            [aoc.core :as aoc]))

(defn parse [s]
  (->> (str/split s #"\n\n")
       (map #(str/split % #"\s"))
       (map
         (fn [items]
           (->> items
                (map #(update (str/split % #":") 0 keyword))
                (into {}))))
       (vec)))

(def input (aoc/with-input "day-04" (comp parse slurp)))
(def required-keys #{:byr :iyr :eyr :hgt :hcl :ecl :pid})

(defn required-keys? [p]
  (set/superset? (set (keys p)) required-keys))

(t/deftest day-04-a
  (t/is (= [{:foo "1" :bar "2"}
            {:baz "3" :quux "4"}]
           (parse "foo:1\nbar:2\n\nbaz:3 quux:4")))
  (t/is (= 190 (count (filter required-keys? input)))))

(t/deftest day-04-b
  (t/is (= 0 0)))
