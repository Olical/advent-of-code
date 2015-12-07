(ns adventofcode.day-3)

(def directions {\> {:x 1  :y 0}
                 \< {:x -1 :y 0}
                 \v {:x 0  :y 1}
                 \^ {:x 0  :y -1}})

(def initial-houses [{:x 0 :y 0}])

(defn visited-houses [initial-instructions]
  (loop [houses initial-houses
         instructions initial-instructions]
    (if (empty? instructions)
      houses
      (let [targets [(first instructions) (last houses)]
            next-house {:x (apply + (map :x targets))
                        :y (apply + (map :y targets))}]
        (recur (conj houses next-house) (rest instructions))))))

(defn count-houses [input]
  (count (distinct (visited-houses (map directions input)))))
