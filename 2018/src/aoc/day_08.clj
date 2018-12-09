(ns aoc.day-08
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(def input
  (aoc/with-lines "day-08" identity
    (fn [lines]
      (->> (str/split (first lines) #"\s")
           (map #(Integer/parseInt %))))))

(defn parse [input]
  (let [[cc mc & input] input]
    (loop [cc cc
           children []
           input input]
      (if (= cc 0)
        (let [[metadata input] (split-at mc input)]
          {:children children
           :metadata metadata
           :input input})
        (let [res (parse input)]
          (recur (dec cc)
                 (conj children res)
                 (:input res)))))))

(def tree
  (parse input))

(defn metadata-sum [tree]
  (reduce +
          (concat (:metadata tree)
                  (map metadata-sum (:children tree)))))

(defn root-node-value [{:keys [children metadata]}]
  (if (empty? children)
    (reduce + metadata)
    (reduce
      (fn [acc n]
        (if-let [child (get children (dec n))]
          (+ acc (root-node-value child))
          acc))
      0
      metadata)))

(t/deftest day-08-a
  (t/testing "input"
    (t/is (= (metadata-sum tree) 47647))))

(t/deftest day-08-b
  (t/testing "input"
    (t/is (= (root-node-value tree) 23636))))
