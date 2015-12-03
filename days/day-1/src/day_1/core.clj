(ns day-1.core)

(defn index-of [item coll]
  (count (take-while (partial not= item) coll)))

(defn parse-parens [instructions]
  (let [char-vals {\( 1
                   \) -1
                   \newline 0}]
    (map char-vals instructions)))

(defn add [acc n]
  (let [last-value (last acc)
        prev (if (number? last-value)
               last-value
               0)]
    (conj acc (+ n prev))))

(defn get-floors [instructions]
  (let [steps (parse-parens instructions)]
    (reduce add [] steps)))

(defn find-floor [instructions]
  (last (get-floors instructions)))

(defn index-of-floor [instructions floor]
  (let [locations (get-floors instructions)]
    (+ 1 (index-of floor locations))))

(defn -main []
  (let [input (slurp "./input.txt")]
    (println (str "Floor: " (find-floor input)))
    (println (str "Index of floor -1: " (index-of-floor input -1)))))
