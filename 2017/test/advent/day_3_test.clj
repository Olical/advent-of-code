(ns advent.day-3-test
  (:require [advent.day-3 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def input (edn/read-string (slurp (io/resource "day-3.txt"))))

(t/deftest part-a
  (t/is (= 0 (sut/spiral 1 :distance)))
  (t/is (= 3 (sut/spiral 12 :distance)))
  (t/is (= 2 (sut/spiral 23 :distance)))
  (t/is (= 31 (sut/spiral 1024 :distance)))
  (t/is (= 480 (sut/spiral input :distance))))

(t/deftest part-b
  (t/is (= 349975M (sut/spiral input :sum-gt))))
