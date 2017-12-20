(ns advent.day-17)

(defn insert-at [coll index value]
  (let [[before after] (split-at index coll)]
    (vec (concat before (list value) after))))

(defn spinlock [step]
  (loop [index 0
         value 1
         buffer [0]]
    (if (> value 2017)
      (get buffer index)
      (let [index (mod (+ index step) (count buffer))]
        (recur (inc index) (inc value) (insert-at buffer index value))))))
