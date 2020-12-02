(ns aoc.day-02
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn parse-policy+pass [s]
  (let [[_ a b req pass]
        (re-matches #"(\d+)-(\d+) ([a-z]): ([a-z]+)" s)]
    {:a (Integer/parseInt a)
     :b (Integer/parseInt b)
     :req req
     :pass pass}))

(def input (aoc/with-lines "day-02" parse-policy+pass vec))

(defn valid-freq? [{:keys [a b req pass]}]
  (let [freq (count (re-seq (re-pattern req) pass))]
    (<= a freq b)))

(defn valid-pos? [{:keys [a b req pass]}]
  (let [req-char (first req)]
    (->> [a b]
         (map #(get pass (dec %)))
         (filter #(= req-char %))
         (count)
         (= 1))))

(t/deftest day-01-a
  (t/is (= {:a 1, :b 3, :req "b", :pass "cdefg"}
           (parse-policy+pass "1-3 b: cdefg")))
  (t/is (valid-freq? {:a 1, :b 3, :req "a", :pass "abcde"}))
  (t/is (not (valid-freq? {:a 1, :b 3, :req "b", :pass "cdefg"})))
  (t/is (valid-freq? {:a 2, :b 9, :req "c", :pass "ccccccccc"}))
  (t/is (= 524 (count (filter valid-freq? input)))))

(t/deftest day-01-b
  (t/is (valid-pos? {:a 1, :b 3, :req "a", :pass "abcde"}))
  (t/is (not (valid-pos? {:a 1, :b 3, :req "b", :pass "cdefg"})))
  (t/is (not (valid-pos? {:a 2, :b 9, :req "c", :pass "ccccccccc"})))
  (t/is (= 485 (count (filter valid-pos? input)))))
