(ns aoc4.core
  (:require [clojure.string :as str]))

(def input (slurp "resources/input.txt"))

(def grid
  (->>
    input
    str/split-lines
    (map #(str/split % #""))
    vec))

(defn rotate-90
  [grid]
  (vec (apply map vector (reverse grid))))

(defn check-left-right
  [grid target]
  (let [lines (map #(apply str %) grid)]
    (apply + (map #(count (re-seq (re-pattern target) %)) lines))))

(defn get-diagonals [grid]
  (let [n (count grid)]
    (->> (concat
           (map (fn [col] (map vector (range n) (range col n))) (range n))
           (map (fn [row] (map vector (range row n) (range n))) (range 1 n)))
         (map (fn [indices] (apply str (map (fn [[i j]] (get-in grid [i j])) indices))))
         vec)))
(def answer-1
  (apply +
         (map #(check-left-right % "XMAS")
              (concat (take 4 (iterate rotate-90 grid))
                      (map get-diagonals (take 4 (iterate rotate-90 grid)))))))

(defn is-x-mas?
  [grid x y]
  (and
    (= (get-in grid [y x]) "M")
    (= (get-in grid [y (+ 2 x)]) "S")
    (= (get-in grid [(inc y) (inc x)]) "A")
    (= (get-in grid [(+ 2 y) x]) "M")
    (= (get-in grid [(+ 2 y) (+ 2 x)]) "S")
    )
  )

(def answer-2
  (apply +
         (map #(count (filter identity (for [x (range (count grid)) y (range (count grid))] (is-x-mas? % x y))))
              (take 4 (iterate rotate-90 grid)))))