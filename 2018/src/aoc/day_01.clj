(ns aoc.day-01
  (:require [clojure.test :as t]
            [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn sum-lines []
  (with-open [rdr (io/reader "inputs/day-01.txt")]
    (->> (line-seq rdr)
         (map edn/read-string)
         (reduce +))))

(t/deftest tests
  (t/testing "01 A"
    (t/is (= (sum-lines) 510)))

  #_(t/testing "01 B"
      (t/is (= (sum-lines) 0))))
