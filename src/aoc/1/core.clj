(ns aoc.1.core
  (:require
    [clojure.java.io :as io]
    [clojure.string :as s]))

(defn get-bags [inp]
  (->> inp
       s/split-lines
       (map (fn [x] (when (not= "" x) (Integer/parseInt x))))
       (partition-by nil?)
       (filter #(some? (first %)))
       (map (partial apply +))
       (apply max)))

(defn solution-1 []
  (-> (io/resource "1_input")
      slurp
      get-bags))
