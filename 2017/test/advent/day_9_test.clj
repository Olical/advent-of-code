(ns advent.day-9-test
  (:require [advent.day-9 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-9.txt")))

(t/deftest part-a
  (t/is (= 1 (sut/score "{}")))
  (t/is (= 3 (sut/score "{{{}}}")))
  (t/is (= 3 (sut/score "{{},{}}")))
  (t/is (= 6 (sut/score "{{{},{},{{}}}}")))
  (t/is (= 1 (sut/score "{<{},{},{{}}>}")))
  (t/is (= 1 (sut/score "{<a>,<a>,<a>,<a>}")))
  (t/is (= 5 (sut/score "{{<a>},{<a>},{<a>},{<a>}}")))
  (t/is (= 2 (sut/score "{{<!>},{<!>},{<!>},{<a>}}"))))
