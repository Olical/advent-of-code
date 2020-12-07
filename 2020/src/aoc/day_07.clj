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

(defn parent? [child-id maybe-parent]
  (some
    (fn [child]
      (= child-id (bag->id child)))
    (:children maybe-parent)))

(defn direct-parents [bags target-id]
  (vec
    (keep
      (fn [bag]
        (when (parent? target-id bag)
          (bag->id bag)))
      bags)))

(defn index [bags]
  (->> bags
       (map
         (fn [bag]
           (let [bag-id (bag->id bag)]
             [bag-id
              (assoc bag :parents (direct-parents bags bag-id))])))
       (into {})))

(def bags (aoc/with-lines "day-07" parse index))

(defn all-parents [target-id]
  (let [{:keys [parents]} (get bags target-id)]
    (concat parents (mapcat all-parents parents))))

(defn root? [bag-id]
  (empty? (get bags [bag-id :parents])))

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
           (parse "faded blue bags contain no other bags.")))
  (t/is (= 213
           (->> (all-parents {:desc "shiny", :colour "gold"})
                (filter root?)
                (distinct)
                (count)))))

(t/deftest day-07-b
  (t/is (= 0 0)))
