(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn with-input [file f]
  (with-open [input (io/reader (format "inputs/%s.txt" file))]
    (f input)))

(defn with-lines [file f]
  (with-input file
    (fn [input]
      (f (line-seq input)))))

(defn with-edn-lines [file f]
  (with-lines file
    (fn [lines]
      (f (map edn/read-string lines)))))
