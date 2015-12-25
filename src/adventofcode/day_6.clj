(ns adventofcode.day-6)

(def size 1000)

(defn xy->i [x y]
  (+ x (* size y)))

(defn get-light [grid x y]
  (get grid (xy->i x y) :off))

(defn set-light [grid x y state]
  (assoc grid (xy->i x y) state))

(defn apply-rule [lights rule]
  lights)

(defn count-enabled-lights [input]
  (let [lights (reduce apply-rule {} input)
        light-nums (map {:on 1 :off 0} lights)]
    (reduce-kv (fn [acc k v] (+ acc v)) 0 lights)))
