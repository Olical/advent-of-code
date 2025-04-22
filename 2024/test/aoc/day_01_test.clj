(ns aoc.day-01-test
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.day-01 :as day-01]))

(t/deftest input-resource
  (t/is (not (str/blank? (slurp day-01/input-resource)))))
