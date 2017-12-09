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

(defn gc [src]
  (loop [mode :normal
         steps (parse src)
         depths []
         depth 1
         garbage 0]
    (if-let [[step & rest-steps] steps]
      (if (= step :skip-next)
        (recur mode (rest rest-steps) depths depth garbage)
        (case mode
          :normal (case step
                    :open-group (recur mode rest-steps (conj depths depth) (inc depth) garbage)
                    :close-group (recur mode rest-steps depths (dec depth) garbage)
                    :open-garbage (recur :garbage rest-steps depths depth garbage)
                    (recur mode rest-steps depths depth garbage))
          :garbage (case step
                     :close-garbage (recur :normal rest-steps depths depth garbage)
                     (recur mode rest-steps depths depth (inc garbage)))))
      {:groups (count depths)
       :score (apply + depths)
       :garbage garbage})))
