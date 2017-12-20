(ns advent.day-17-test
  (:require [advent.day-17 :as sut]
            [clojure.test :as t]))

(def input 303)

(t/deftest part-a
  (t/is (= 638 (sut/spinlock 3)))
  (t/is (= 1971 (sut/spinlock input))))

(t/deftest part-b
  (t/is (= nil (sut/ANGRY-SPINLOCK input))))
