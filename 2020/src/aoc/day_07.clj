(ns aoc.day-07
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [aoc.core :as aoc]))

(defn bag->id [bag]
  (select-keys bag #{:desc :colour}))

(defn parse [s]
  (let [[_ desc colour children]
        (re-matches #"(\w+) (\w+) bags contain (.*)\." s)
        children (when (not= "no other bags" children)
                   (->> (str/split children #", ")
                        (mapv
                          (fn [child]
                            (-> (zipmap [:amount :desc :colour]
                                        (rest (re-find #"(\d+) (\w+) (\w+)" child)))
                                (update :amount #(Integer/parseInt %)))))))]

    {:desc desc
     :colour colour
     :children children}))

(def input (aoc/with-lines "day-07" parse vec))

(t/deftest day-07-a
  (t/is (= {:desc "bright"
            :colour "white"
            :children [{:amount 1, :desc "shiny", :colour "gold"}]}
           (parse "bright white bags contain 1 shiny gold bag.")))
  (t/is (= {:desc "vibrant"
            :colour "plum"
            :children [{:amount 5, :desc "faded", :colour "blue"}
                       {:amount 6, :desc "dotted", :colour "black"}]}
           (parse "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.")))
  (t/is (= {:desc "faded", :colour "blue", :children nil}
           (parse "faded blue bags contain no other bags."))))

(t/deftest day-07-b
  (t/is (= 0 0)))
