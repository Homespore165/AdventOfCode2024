(ns aoc2.core
  (:require [clojure.string :as str]
            [clojure.edn :as edn]))

(def input (slurp "resources/input.txt"))

(def input-lines
  (->> input
       str/split-lines
       (map #(map edn/read-string (str/split % #"\s+")))))

(defn max-difference [coll]
  (apply max (map #(abs (- %1 %2)) coll (rest coll))))

(defn min-difference [coll]
  (apply min (map #(abs (- %1 %2)) coll (rest coll))))

(defn safe? [coll]
  (and (or (apply <= coll) (apply >= coll))
       (<= (max-difference coll) 3)
       (>= (min-difference coll) 1)))

(def answer-1 (count (filter safe? input-lines)))

(defn problem-dampen [pred coll]
  (or (pred coll)
      (some pred (for [i (range (count coll))]
                   (concat (take i coll) (drop (inc i) coll))))))

(def answer-2 (count (filter #(problem-dampen safe? %) input-lines)))