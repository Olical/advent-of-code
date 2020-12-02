(ns aoc.day-02
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn parse-policy+pass [s]
  (let [[_ min-str max-str req pass]
        (re-matches #"(\d+)-(\d+) ([a-z]): ([a-z]+)" s)]
    {:min (Integer/parseInt min-str)
     :max (Integer/parseInt max-str)
     :req req
     :pass pass}))

(def input (aoc/with-lines "day-02" parse-policy+pass vec))

(defn valid-freq? [{:keys [min max req pass]}]
  (let [freq (count (re-seq (re-pattern req) pass))]
    (<= min freq max)))

(t/deftest day-01-a
  (t/is (= {:min 1, :max 3, :req "b", :pass "cdefg"}
           (parse-policy+pass "1-3 b: cdefg")))
  (t/is (valid-freq? {:min 1, :max 3, :req "a", :pass "abcde"}))
  (t/is (not (valid-freq? {:min 1, :max 3, :req "b", :pass "cdefg"})))
  (t/is (valid-freq? {:min 2, :max 9, :req "c", :pass "ccccccccc"}))
  (t/is (= 524 (count (filter valid-freq? input)))))

(t/deftest day-01-b
  (t/is (= true true)))
