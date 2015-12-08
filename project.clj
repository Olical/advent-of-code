(defproject adventofcode "0.1.0-SNAPSHOT"
  :description "Oliver Caldwell's attempts at the Advent of Code"
  :url "http://adventofcode.com/"
  :license {:name "Unlicense"
            :url "http://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [digest "1.4.4"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :plugins [[lein-auto "0.1.2"]])
