(ns aoc.4.core
  (:require
    [clojure.java.io :as io]
    [clojure.string :as str]))

(defn parse-line [line]
  (->> (str/split line #",")
       (map (fn [rnge] (str/split rnge #"-")))
       (map (fn [ranges] (map #(Integer/parseInt %) ranges)))
       vec))

(defn is-surrounded [[[l-start l-end] [r-start r-end]]]
  (or
    (<= l-start r-start r-end l-end)
    (<= r-start l-start l-end r-end)))

(defn overlaps [[[l-start l-end] [r-start r-end]]]
  (or
    (<= l-start r-start l-end)
    (<= r-start l-start r-end)))

(defn solution [pred]
  (->> (io/resource "4_input")
       slurp
       str/split-lines
       (map parse-line)
       (filter pred)
       count))

(defn solution-4-1 []
  (solution is-surrounded))

(defn solution-4-2 []
  (solution overlaps))
