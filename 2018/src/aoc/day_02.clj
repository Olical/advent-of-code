(ns aoc.day-02
  (:require [clojure.test :as t]))

(defmacro with-input [bind & body]
  `(with-open [rdr# (io/reader "inputs/day-02.txt")]
     (let [~bind (map edn/read-string (line-seq rdr#))]
       ~@body)))

(defn solution []
  nil)

(t/deftest tests
  (t/testing "02 A"
    (t/is (= (solution) 0)))

  (t/testing "02 B"
    (t/is (= (solution) 0))))
