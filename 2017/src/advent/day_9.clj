(ns advent.day-9)

(defn parse [src]
  (map (fn [c]
         (case c
           \{ :open-group
           \} :close-group
           \< :open-garbage
           \> :close-garbage
           \, :separator
           \! :skip-next
           :?))
       src))

(defn drop-until [target steps]
  (drop-while #(not= target %) steps))

(defn depths [src]
  (loop [mode :normal
         steps (parse src)
         depths []
         depth 1]
    (if-let [[step & rest-steps] steps]
      (if (= step :skip-next)
        (recur mode (rest rest-steps) depths depth)
        (case mode
          :normal (case step
                    :open-group (recur mode rest-steps (conj depths depth) (inc depth))
                    :close-group (recur mode rest-steps depths (dec depth))
                    :open-garbage (recur :garbage rest-steps depths depth)
                    (recur mode rest-steps depths depth))
          :garbage (case step
                     :close-garbage (recur :normal rest-steps depths depth)
                     (recur mode rest-steps depths depth))))
      depths)))
