(ns advent.day-4-test
  (:require [advent.day-4 :as sut]
            [clojure.java.io :as io]
            [clojure.test :as t]))

(def input (slurp (io/resource "day-4.txt")))

(t/deftest part-a
  (t/is (= 1 (sut/valid-phrase-count "aa bb cc dd ee" identity)))
  (t/is (= 1 (sut/valid-phrase-count "aa bb cc dd aaa" identity)))
  (t/is (= 0 (sut/valid-phrase-count "aa bb cc dd aa" identity)))
  (t/is (= 451 (sut/valid-phrase-count input identity))))

(t/deftest part-b
  (t/is (= 1 (sut/valid-phrase-count "abcde fghij" sort)))
  (t/is (= 0 (sut/valid-phrase-count "abcde xyz ecdab" sort)))
  (t/is (= 1 (sut/valid-phrase-count "a ab abc abd abf abj" sort)))
  (t/is (= 1 (sut/valid-phrase-count "iiii oiii ooii oooi oooo" sort)))
  (t/is (= 0 (sut/valid-phrase-count "oiii ioii iioi iiio" sort)))
  (t/is (= 223 (sut/valid-phrase-count input sort))))
