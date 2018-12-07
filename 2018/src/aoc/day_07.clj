(ns aoc.day-07
  (:require [clojure.test :as t]
            [clojure.set :as set]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(def step-re #"Step (\w) must be finished before step (\w) can begin.")

(defn parse-step [s]
  (->> s
       (re-matches step-re)
       (rest)
       (mapv keyword)))

(def input
  (aoc/with-lines "day-07" parse-step vec))

(def steps
  (into #{} (mapcat identity) input))

(def deps
  (->> input
       (mapv (fn [[a b]] {b #{a}}))
       (apply merge-with
              set/union
              (zipmap steps (repeat #{})))))

(defn available [completed steps]
  (->> steps
       (filter
         (fn [step]
           (set/superset? completed (get deps step))))
       (sort)
       (first)))

(defn step-string []
  (loop [steps steps
         acc []]
    (if-let [step (available (set acc) steps)]
      (recur (disj steps step) (conj acc step))
      (->> acc (map name) (str/join)))))

(def base-time 60)

(def times
  (->> (range 65 91)
       (into {}
             (map
               (fn [n]
                 [(-> n char str keyword) (+ n -64 base-time)])))))

(defn advance [steps workers done]
  (loop [steps steps
         workers workers
         done done
         i (dec (count workers))]
    (if (>= i 0)
      (let [worker (get workers i)
            step (available done steps)]
        (cond
          ;; Assign work.
          (and (= worker :ready) step)
          (recur (disj steps step)
                 (assoc workers i [step (get times step)])
                 done
                 (dec i))

          ;; Do nothing. No work left.
          (and (= worker :ready) (not step))
          (recur steps
                 workers
                 done
                 (dec i))

          ;; Move work to done, set to ready and re-attempt.
          (<= (second worker) 1)
          (recur steps
                 (assoc workers i :ready)
                 (conj done (first worker))
                 i)

          ;; Just decrement the work remaining.
          :else
          (recur steps
                 (update-in workers [i 1] dec)
                 done
                 (dec i))))
      [steps workers done])))

(defn parallel-time []
  (loop [steps steps
         workers [:ready :ready :ready :ready :ready]
         done #{}
         tick 0]
    (if (or (seq steps) (apply not= :ready workers))
      (let [[steps workers done] (advance steps workers done)]
        (recur steps workers done (inc tick)))
      (dec tick))))

(t/deftest day-07-a
  (t/testing "input"
    (t/is (= (step-string) "GJKLDFNPTMQXIYHUVREOZSAWCB"))))

(t/deftest day-07-b
  (t/testing "input"
    (t/is (= (parallel-time) 967))))
