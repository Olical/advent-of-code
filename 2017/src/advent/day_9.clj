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

(defn score [src]
  (parse src))
