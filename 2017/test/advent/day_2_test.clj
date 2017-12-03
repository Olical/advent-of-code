(ns advent.day-2-test
  (:require [advent.day-2 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-2.txt")))

(def example-a "5 1 9 5
7 5 3
2 4 6 8")

(def example-b "5 9 2 8
9 4 7 3
3 8 6 5")

(t/deftest part-a
  (t/is (= 18 (sut/checksum example-a sut/row-min-max)))
  (t/is (= 42299 (sut/checksum input sut/row-min-max))))

#_(t/deftest part-b
  (let [row-fn (fn [row]
                 )]
    (t/is (= 9 (sut/checksum example-b row-fn)))))
