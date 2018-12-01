(ns aoc.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defmacro with-input [bind file & body]
  `(with-open [~bind (io/reader (format "inputs/%s.txt" ~file))]
     ~@body))

(defmacro with-lines [bind file & body]
  `(with-input input# ~file
     (let [~bind (line-seq input#)]
       ~@body)))

(defmacro with-edn-lines [bind file & body]
  `(with-lines lines# ~file
     (let [~bind (map edn/read-string lines#)]
       ~@body)))
