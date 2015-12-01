(ns day-1.core)

(defn find-floor [instructions]
  (reduce +
          (remove nil?
                  (map {\( 1
                        \) -1} instructions))))

(defn -main []
  (println (find-floor (slurp "./input.txt"))))
