(ns adventofcode.day-4
  (:require [digest]))

(defn hash-hex [input]
  (let [hash (digest/md5 input)]
    (format "%x" (new java.math.BigInteger (.getBytes hash)))))

(defn starts-with-zeros [input]
  (every? #(= \0 %) (take 5 input)))

(defn mine-advent [secret]
  (hash-hex secret))
