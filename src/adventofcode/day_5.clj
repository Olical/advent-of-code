(ns adventofcode.day-5)

(defn is-nice? [input]
  false)

(def bool-num {false 0
               true 1})

(defn count-nice [input]
  (reduce + (map bool-num (map is-nice? input))))
