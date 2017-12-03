(ns advent.day-3-test
  (:require [advent.day-3 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(def input (edn/read-string (slurp (io/resource "day-3.txt"))))

(t/deftest part-a
  (t/is (= 0 (sut/distance 1)))
  (t/is (= 3 (sut/distance 12)))
  (t/is (= 2 (sut/distance 23)))
  (t/is (= 31 (sut/distance 1024)))
  (t/is (= 480 (sut/distance input))))
