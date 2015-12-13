(ns adventofcode.day-5)

(def vowles #{\a \e \i \o \u})

(def bool-num {true 1
               false 0})

(def bad-substrs #{"ab"
                   "cd"
                   "pq"
                   "xy"})

(defn count-vowles [coll]
  (count (filter #(contains? vowles %) coll)))

(defn has-sequential? [coll]
  (boolean (re-find #"(.)\1+" coll)))

(defn has-bad-substr? [input]
  (boolean (some true? (map #(.contains input %) bad-substrs))))

(defn is-nice? [input]
  (and
    (>= (count-vowles input) 3)
    (has-sequential? input)
    (not (has-bad-substr? input))))

(defn count-nice [input]
  (reduce + (map bool-num (map is-nice? input))))
