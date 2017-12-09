(ns advent.day-9-test
  (:require [advent.day-9 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-9.txt")))

(t/deftest part-a
  (t/is (= 1 (count (sut/depths "{}"))))
  (t/is (= 3 (count (sut/depths "{{{}}}"))))
  (t/is (= 3 (count (sut/depths "{{},{}}"))))
  (t/is (= 6 (count (sut/depths "{{{},{},{{}}}}"))))
  (t/is (= 1 (count (sut/depths "{<{},{},{{}}>}"))))
  (t/is (= 1 (count (sut/depths "{<a>,<a>,<a>,<a>}"))))
  (t/is (= 5 (count (sut/depths "{{<a>},{<a>},{<a>},{<a>}}"))))
  (t/is (= 2 (count (sut/depths "{{<!>},{<!>},{<!>},{<a>}}"))))

  (t/is (= 1 (apply + (sut/depths "{}"))))
  (t/is (= 6 (apply + (sut/depths "{{{}}}"))))
  (t/is (= 5 (apply + (sut/depths "{{},{}}"))))
  (t/is (= 16 (apply + (sut/depths "{{{},{},{{}}}}"))))
  (t/is (= 1 (apply + (sut/depths "{<a>,<a>,<a>,<a>}"))))
  (t/is (= 9 (apply + (sut/depths "{{<ab>},{<ab>},{<ab>},{<ab>}}"))))
  (t/is (= 9 (apply + (sut/depths "{{<!!>},{<!!>},{<!!>},{<!!>}}"))))
  (t/is (= 3 (apply + (sut/depths "{{<a!>},{<a!>},{<a!>},{<ab>}}"))))

  (t/is (= 16869 (apply + (sut/depths input)))))
