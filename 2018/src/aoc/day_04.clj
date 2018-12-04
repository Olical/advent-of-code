(ns aoc.day-04
  (:require [clojure.test :as t]
            [clojure.edn :as edn]
            [aoc.core :as aoc]))

(def entry-re #"\[([\d\-\s:]+)\] (.*)")
(def id-re #"Guard #(\d+) begins shift")
(def minute-re #".*:0*(\d+)")

(defn parse-entry [s]
  (let [[_ at action] (re-matches entry-re s)
        id (-> (re-matches id-re action) (second) (edn/read-string))
        minute (-> (re-matches minute-re at) (second) (edn/read-string))]
    {:at at
     :minute minute
     :action (cond
               (= action "falls asleep") :falls-asleep
               (= action "wakes up") :wakes-up
               :else [:starts-shift id])}))

(defn guards []
  (->> (aoc/with-lines "day-04" parse-entry
         (fn [entries]
           (loop [entries (sort-by :at entries)
                  guards []]
             (if (seq entries)
               (let [[entry & entries] entries
                     [phases entries] (split-with (comp #{:falls-asleep :wakes-up} :action) entries)
                     sleep (->> phases
                                (map :minute)
                                (partition 2)
                                (mapcat
                                  (fn [[start end]]
                                    (range start end))))]
                 (recur entries
                        (conj guards {:id (-> entry :action second)
                                      :sleep sleep})))
               guards))))
       (group-by :id)
       (map (fn [[id group]]
              {:id id
               :sleep (mapcat :sleep group)}))))

(defn sleepy-hash []
  (let [{:keys [id sleep]} (->> (guards)
                                (sort-by (comp count :sleep))
                                (last))
        minute (->> (frequencies sleep) (sort-by second) (last) (first))]
    (* id minute)))

(t/deftest day-04-a
  (t/testing "input"
    (t/is (= (sleepy-hash) 26281))))

(t/deftest day-04-b
  (t/testing "input"
    (t/is (= 0 0))))
