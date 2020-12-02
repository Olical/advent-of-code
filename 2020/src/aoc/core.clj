(ns aoc.core
  (:require [clojure.java.io :as io]))

(defn with-input [file f]
  (with-open [input (io/reader (format "inputs/%s.txt" file))]
    (f input)))

(defn with-lines [file map-fn f]
  (with-input file
    #(f (map map-fn (line-seq %)))))

;; Template
; (ns aoc.day-XX
;   (:require [clojure.test :as t]
;             [aoc.core :as aoc]))

; (t/deftest day-XX-a
;   (t/is (= 0 0)))

; (t/deftest day-XX-b
;   (t/is (= 0 0)))
