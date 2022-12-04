(ns aoc.2.core
  (:require
    [clojure.java.io :as io]
    [clojure.string :as s]))

(def opp->shape
  {"A" :rock
   "B" :paper
   "C" :scissors})

(def own->shape
  {"X" :rock
   "Y" :paper
   "Z" :scissors})

(def result
  {[:rock :paper]        :win
   [:rock :scissors]     :loss
   [:rock :rock]         :tie
   [:paper :paper]       :tie
   [:paper :rock]        :loss
   [:paper :scissors]    :win
   [:scissors :paper]    :loss
   [:scissors :scissors] :tie
   [:scissors :rock]     :win})

(def result->score
  {:loss 0
   :tie  3
   :win  6})

(def shape->score
  {:rock     1
   :paper    2
   :scissors 3})

(defn parse-line [ln]
  (let [[opp own] (s/split ln #"\s")]
    [(opp->shape opp) (own->shape own)]))

(defn parse-lines [inp]
  (->> (s/split-lines inp)
       (map parse-line)))

(defn get-score-per-line [ln]
  (let [result (result ln)
        my-shape (second ln)]
    (+ (result->score result)
       (shape->score my-shape))))

(defn solution-2 []
  (let [inp (-> (io/resource "2_input") slurp)
        rounds (parse-lines inp)
        score-per-line (map get-score-per-line rounds)]
    (apply + score-per-line)))
