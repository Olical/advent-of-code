(ns adventofcode.day-6
  (:require [clojure.string :as string]))

(def size 1000)

(def state-to-bool {:on 1
                    :off 0})

(def str-to-state {"on" :on
                   "off" :off
                   "toggle" :toggle})

(def invert-state {:on :off
                   :off :on})

(defn xy->i [x y]
  (+ x (* size y)))

(defn get-light [grid x y]
  (get grid (xy->i x y) :off))

(defn set-light [grid x y state]
  (if (= state :toggle)
    (set-light grid x y (invert-state (get-light grid x y)))
    (assoc grid (xy->i x y) state)))

(defn parse-coord [coord]
  (if (nil? coord)
    coord
    (let [parts (string/split coord #",")]
      {:x (Integer/parseInt (parts 0))
       :y (Integer/parseInt (parts 1))})))

(defn parse-rule [rule-str]
  (let [raw (string/split rule-str #"\s")
        parts (if (= "turn" (first raw))
                (into [] (rest raw))
                raw)]
    {:from (parse-coord (get parts 1))
     :to (parse-coord (get parts 3))
     :state (str-to-state (first parts))}))

(defn apply-rule [lights rule-str]
  (let [rule (parse-rule rule-str)]
    lights))

(defn count-enabled-lights [input]
  (let [lights (reduce apply-rule {} input)
        light-nums (map state-to-bool lights)]
    (reduce-kv (fn [acc k v] (+ acc v)) 0 lights)))
