(ns aoc.core
  (:require [clojure.java.io :as io]))

(defn with-input [file f]
  (with-open [input (io/reader (format "inputs/%s.txt" file))]
    (f input)))

(defn with-lines [file map-fn f]
  (with-input file
    #(f (map map-fn (line-seq %)))))

(defn abs "(abs n) is the absolute value of n" [n]
  (cond
    (not (number? n)) (throw (IllegalArgumentException.
                               "abs requires a number"))
    (neg? n) (- n)
    :else n))

;; Template
; (ns aoc.day-XX
;   (:require [clojure.test :as t]
;             [aoc.core :as aoc]))

; (t/deftest day-XX-a
;   (t/testing "input"
;     (t/is (= 0 0))))

; (t/deftest day-XX-b
;   (t/testing "input"
;     (t/is (= 0 0))))
