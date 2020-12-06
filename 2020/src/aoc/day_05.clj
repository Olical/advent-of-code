(ns aoc.day-05
  (:require [clojure.test :as t]
            [aoc.core :as aoc]))

(defn parse [s]
  (zipmap [:row-steps :col-steps]
          (->> s
               (map {\F :- \B :+
                     \L :- \R :+})
               (split-at 7))))

(def input (aoc/with-lines "day-05" parse vec))

(defn search [{:keys [row-steps col-steps]}]
  (letfn [(walk [space steps]
            (if (empty? steps)
              (first space)
              (let [[step & steps] steps
                    half-space (/ (count space) 2)]
                (recur
                  (case step
                    :- (take half-space space)
                    :+ (drop half-space space))
                  steps))))]
    (let [row (walk (range 128) row-steps)
          col (walk (range 8) col-steps)]
      {:row row
       :col col
       :id (+ (* row 8) col)})))

(t/deftest day-05-a
  (t/is (= {:row 70, :col 7, :id 567}
           (search (parse "BFFFBBFRRR"))))
  (t/is (= {:row 14, :col 7, :id 119}
           (search (parse "FFFBBBFRRR"))))
  (t/is (= 947 (:id (last (sort-by :id (map search input)))))))

(t/deftest day-05-b
  (t/is (= 0 0)))
