(ns aoc.day-02
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(do 
  (defn checksum []
    (aoc/with-lines "day-02" frequencies
      (fn [freqs]
        (let [dupes (reduce
                      (fn [acc freqs]
                        (merge-with (fn [x _] (inc x))
                                    acc
                                    (zipmap (vals freqs) (repeat 1))))
                      {}
                      freqs)]
          (* (get dupes 2) (get dupes 3))))))

  (checksum))

(t/deftest day-02-a
  (t/testing "input"
    (t/is (= (checksum) 7105))))

(t/deftest day-02-b
  (t/testing "input"
    (t/is (= 0 0))))
