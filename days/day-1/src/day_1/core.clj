(ns day-1.core)

(def instruction-values
  {\( 1
   \) -1
   \newline 0})

(defn parse-instructions [instructions]
  (map instruction-values (slurp "./input.txt")))

(defn add-to-acc [acc x]
  (conj acc (+ x (last acc))))

(defn get-floors [instructions]
  (reduce add-to-acc [0] (parse-instructions instructions)))

(defn -main []
  (let [floors (get-floors (slurp "./input.txt"))]
    (println "Last floor: " (last floors))
    (println "First time at -1: " (+ 1 (.indexOf floors -1)))))
