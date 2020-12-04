(ns aoc.day-04
  (:require [clojure.test :as t]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.spec.alpha :as s]
            [aoc.core :as aoc]))

(defn parse-int [s]
  (try
    (Integer/parseInt s)
    (catch Exception _
      nil)))

(defn update-when [x k f]
  (if (contains? x k)
    (update x k f)
    x))

(defn parse [s]
  (->> (str/split s #"\n\n")
       (map #(str/split % #"\s"))
       (map
         (fn [items]
           (-> items
               (->> (map #(update (str/split % #":") 0 keyword))
                    (into {}))
               (update-when :byr parse-int)
               (update-when :iyr parse-int)
               (update-when :eyr parse-int)
               (update-when
                 :hgt
                 (fn [s]
                   (when s
                     (when-let [[_ n unit] (re-matches #"(\d+)(cm|in)" s)]
                       {:height-unit unit
                        :height (parse-int n)})))))))
       (vec)))

(def input (aoc/with-input "day-04" (comp parse slurp)))
(def required-keys #{:byr :iyr :eyr :hgt :hcl :ecl :pid})

(defn valid-keys? [p]
  (set/superset? (set (keys p)) required-keys))

(s/def ::byr (s/and int? #(<= 1920 % 2002)))
(s/def ::iyr (s/and int? #(<= 2010 % 2020)))
(s/def ::eyr (s/and int? #(<= 2020 % 2030)))
(s/def ::hgt (fn [{:keys [height-unit height]}]
               (case height-unit
                 "cm" (<= 150 height 193)
                 "in" (<= 59 height 76)
                 nil)))
(s/def ::hcl (s/and string? #(re-matches #"#[a-f0-9]{6}" %)))
(s/def ::ecl (s/and string? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"}))
(s/def ::pid (s/and string? #(re-matches #"\d{9}" %)))
(s/def ::passport (s/keys :req-un [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid]))

(t/deftest day-04-a
  (t/is (= [{:foo "1" :bar "2"}
            {:baz "3" :quux "4"}]
           (parse "foo:1\nbar:2\n\nbaz:3 quux:4")))
  (t/is (= 190 (count (filter valid-keys? input)))))

(t/deftest day-04-b
  (t/is (not (s/valid? ::passport (first (parse "eyr:1972 cid:100\nhcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926")))))
  (t/is (s/valid? ::passport (first (parse "eyr:2029 ecl:blu cid:129 byr:1989\niyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"))))
  (t/is (= 121 (count (filter #(s/valid? ::passport %) input)))))
