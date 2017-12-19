(ns advent.day-15-test
  (:require [advent.day-15 :as sut]
            [clojure.test :as t]))

(def input {:a 512, :b 191})

(def example {:a 65, :b 8921})

(t/deftest part-a
  (t/is (= 588 (sut/judge example)))
  (t/is (= 567 (sut/judge input))))

(t/deftest part-b
  (t/is (= 309 (sut/picky-judge example)))
  (t/is (= 323 (sut/picky-judge input))))
