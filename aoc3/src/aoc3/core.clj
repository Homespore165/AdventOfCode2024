(ns aoc3.core
  (:require [clojure.edn :as edn])
  (:require [clojure.string :as str]))

(def input (slurp "resources/input.txt"))
(def mults (re-seq #"mul\(\d{1,3},\d{1,3}\)" input))
(def answer-1 (apply + (map #(apply * (map edn/read-string (re-seq #"\d+" %))) mults)))
(def do-mults (re-seq #"mul\(\d{1,3},\d{1,3}\)" (str/replace input #"(?s)don't\(\).*?do\(\)" "")))
(def answer-2 (apply + (map #(apply * (map edn/read-string (re-seq #"\d+" %))) do-mults)))