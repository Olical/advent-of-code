(ns adventofcode.day-4
  (:require [digest]))

(defn starts-with-zeros [amount input]
  (every? #(= \0 %) (take amount input)))

(defn attempt-guesses [zeros secret guesses]
  (let [guess (first guesses)
        attempt (digest/md5 (str secret guess))]
    (if (starts-with-zeros zeros attempt)
      guess
      (if (empty? guesses)
        nil
        (recur zeros secret (rest guesses))))))

(defn mine-advent [zeros secret]
  (let [base 3000000
        ranges [(range 0 base)
                (range base (* 2 base))
                (range (* 2 base) (* 3 base))
                (range (* 3 base) (* 4 base))]
        guesser (partial attempt-guesses zeros secret)
        results (pmap guesser ranges)]
    (first (remove nil? results))))
