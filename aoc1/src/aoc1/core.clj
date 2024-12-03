(ns aoc1.core)
(require '[clojure.string :as str]
         '[clojure.edn :as edn])

(def input (str/split-lines (slurp "resources/input.txt")))

(def left-list (->> input
                    (map #(first (str/split % #" ")))
                    (map edn/read-string)
                    sort))

(def right-list (->> input
                     (map #(last (str/split % #" ")))
                     (map edn/read-string)
                     sort))

(def answer-1 (apply + (map abs (map - left-list right-list))))

(def left-map (frequencies left-list))
(def right-map (frequencies right-list))

(def answer-2 (apply + (map (fn [[k v]] (* k (get right-map k 0))) left-map)))