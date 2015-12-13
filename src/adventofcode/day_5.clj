(ns adventofcode.day-5)

(def vowles #{\a \e \i \o \u})

(def bad-substrs #{"ab"
                   "cd"
                   "pq"
                   "xy"})

(def bool-num {false 0
               true 1})

(defn count-vowles [coll]
  (count (filter #(contains? vowles %) coll)))

(defn has-sequential? [coll]
  (re-find #"(.)\1+" coll))

(defn is-nice? [input]
  (and
    (>= 3 (count-vowles input))
    (not (= nil (has-sequential? input)))
    (every? false? (map #(.contains input %) bad-substrs))))

(defn count-nice [input]
  (reduce + (map bool-num (map is-nice? input))))
