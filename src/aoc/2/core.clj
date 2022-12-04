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

(def dired->result
  {"X" :loss
   "Y" :tie
   "Z" :win})

(defn ->dired-shape [opp dired]
  (if (= :tie dired)
    opp
    (condp = [opp dired]
      [:rock :win] :paper
      [:rock :loss] :scissors
      [:paper :win] :scissors
      [:paper :loss] :rock
      [:scissors :win] :rock
      [:scissors :loss] :paper
      :default opp)))

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

(defn parse-line [get-second ln]
  (let [[opp own] (s/split ln #"\s")]
    [(opp->shape opp) (get-second own)]))

(defn parse-lines [inp parse]
  (->> (s/split-lines inp)
       (map parse)))

(defn get-score-per-line [ln]
  (let [result (result ln)
        my-shape (second ln)]
    (+ (result->score result)
       (shape->score my-shape))))

(defn get-score-per-line-2 [[opp dired]]
  (let [my-shape (->dired-shape opp dired)]
    (+ (result->score dired)
       (shape->score my-shape))))

(defn solution-2-1 []
  (let [inp (-> (io/resource "2_input") slurp)
        rounds (parse-lines inp (partial parse-line own->shape))
        score-per-line (map get-score-per-line rounds)]
    (apply + score-per-line)))

(defn solution-2-2 []
  (let [inp (-> (io/resource "2_input") slurp)
        rounds (parse-lines inp (partial parse-line dired->result))
        score-per-line (map get-score-per-line-2 rounds)]
    (apply + score-per-line)))
