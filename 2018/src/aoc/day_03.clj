(ns aoc.day-03
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [clojure.set :as set]
            [aoc.core :as aoc]))

(def claim-re #"#(\d+) @ (\d+),(\d+): (\d+)x(\d+)")

(defn parse-claim [claim]
  (let [[id x y w h] (->> (re-matches claim-re claim) (rest) (map edn/read-string))]
    (for [x (range x (+ x w))
          y (range y (+ y h))]
      {:id id
       :pos [x y]})))

(defn grid []
  (aoc/with-lines "day-03" parse-claim
    (fn [claims]
      (loop [[claim & claims] (apply concat claims)
             acc (transient {})]
        (if-let [{:keys [id pos]} claim]
          (let [current (get acc pos)]
            (recur claims
                   (assoc! acc pos (conj current id))))
          (persistent! acc))))))

(defn shared-inches []
  (reduce
    (fn [acc shared]
      (if (> shared 1)
        (+ acc 1)
        acc))
    0
    (->> (grid) (vals) (map count))))

(defn no-overlap []
  (let [ids (vals (grid))
        overlapped (into #{} (apply concat (filter #(> (count %) 1) ids)))
        potential (into #{} (apply concat (filter #(= (count %) 1) ids)))]
    (first (set/difference potential overlapped))))

(t/deftest day-03-a
  (t/testing "input"
    (t/is (= (shared-inches) 112378))))

(t/deftest day-03-b
  (t/testing "input"
    (t/is (= (no-overlap) 603))))
