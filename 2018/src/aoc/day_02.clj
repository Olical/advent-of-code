(ns aoc.day-02
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

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

(defn common-chars [as bs]
  (reduce (fn [acc [a b]]
            (if (= a b)
              (conj acc a)
              acc))
          []
          (map vector as bs)))

(defn correct-id-common-chars []
  (aoc/with-lines "day-02" identity
    (fn [ids]
      (some
        (fn [a]
          (some
            (fn [b]
              (let [cs (common-chars a b)]
                (when (= (count cs) (dec (count a)))
                  (str/join cs))))
            ids))
        ids))))

(t/deftest day-02-a
  (t/testing "input"
    (t/is (= (checksum) 7105))))

(t/deftest day-02-b
  (t/testing "input"
    (t/is (= (correct-id-common-chars) "omlvgdokxfncvqyersasjziup"))))
