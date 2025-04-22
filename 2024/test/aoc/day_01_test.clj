(ns aoc.day-01-test
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.day-01 :as day-01]))

(t/deftest input-resource
  (t/is (not (str/blank? (slurp day-01/input-resource)))))

(t/deftest parse-row
  (t/is (= '(80414 72092) (day-01/parse-row "80414   72092"))))

(t/deftest difference
  (t/is (= 5 (day-01/difference 10 5)))
  (t/is (= 5 (day-01/difference 5 10))))

(t/deftest part-1
  (t/is (= 1646452 (day-01/part-1 day-01/input-resource))))
