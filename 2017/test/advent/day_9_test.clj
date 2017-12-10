(ns advent.day-9-test
  (:require [advent.day-9 :as sut]
            [clojure.test :as t]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day-9.txt")))

(t/deftest part-a
  (t/is (= 1 (:groups (sut/gc "{}"))))
  (t/is (= 3 (:groups (sut/gc "{{{}}}"))))
  (t/is (= 3 (:groups (sut/gc "{{},{}}"))))
  (t/is (= 6 (:groups (sut/gc "{{{},{},{{}}}}"))))
  (t/is (= 1 (:groups (sut/gc "{<{},{},{{}}>}"))))
  (t/is (= 1 (:groups (sut/gc "{<a>,<a>,<a>,<a>}"))))
  (t/is (= 5 (:groups (sut/gc "{{<a>},{<a>},{<a>},{<a>}}"))))
  (t/is (= 2 (:groups (sut/gc "{{<!>},{<!>},{<!>},{<a>}}"))))

  (t/is (= 1 (:score (sut/gc "{}"))))
  (t/is (= 6 (:score (sut/gc "{{{}}}"))))
  (t/is (= 5 (:score (sut/gc "{{},{}}"))))
  (t/is (= 16 (:score (sut/gc "{{{},{},{{}}}}"))))
  (t/is (= 1 (:score (sut/gc "{<a>,<a>,<a>,<a>}"))))
  (t/is (= 9 (:score (sut/gc "{{<ab>},{<ab>},{<ab>},{<ab>}}"))))
  (t/is (= 9 (:score (sut/gc "{{<!!>},{<!!>},{<!!>},{<!!>}}"))))
  (t/is (= 3 (:score (sut/gc "{{<a!>},{<a!>},{<a!>},{<ab>}}"))))

  (t/is (= 16869 (:score (sut/gc input)))))

(t/deftest part-b
  (t/is (= 0 (:garbage (sut/gc "<>"))))
  (t/is (= 17 (:garbage (sut/gc "<random characters>"))))
  (t/is (= 3 (:garbage (sut/gc "<<<<>"))))
  (t/is (= 2 (:garbage (sut/gc "<{!>}>"))))
  (t/is (= 0 (:garbage (sut/gc "<!!>"))))
  (t/is (= 0 (:garbage (sut/gc "<!!!>>"))))
  (t/is (= 10 (:garbage (sut/gc "<{o\"i!a,<{i<a>"))))

  (t/is (= 7284 (:garbage (sut/gc input)))))
