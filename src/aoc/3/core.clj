(ns aoc.3.core
  (:require
    [clojure.java.io :as io]
    [clojure.set :refer [intersection]]
    [clojure.string :as s]))

(defn get-priority [c]
  (let [chr (int c)]
    (if (< chr 97)
      (- chr 38)
      (- chr 96))))

(defn find-duplicate [rucksack]
  (let [[left right] (split-at (/ (count rucksack) 2) rucksack)]
    (first (intersection (set left) (set right)))))

(defn find-badge [lines]
  (first (apply intersection (map set lines))))

(defn solution-3-1 []
  (apply +
         (->> (io/resource "3_input")
              slurp
              s/split-lines
              (map find-duplicate)
              (map get-priority))))

(defn solution-3-2 []
  (apply +
         (->> (io/resource "3_input")
              slurp
              s/split-lines
              (partition 3)
              (map find-badge)
              (map get-priority))))
