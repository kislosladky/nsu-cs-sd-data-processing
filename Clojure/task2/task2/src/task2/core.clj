(ns task2.core
  (:gen-class)
  (:require [clojure.test :refer :all]))

  
(defn sieve [stream]
  (lazy-seq
   (let [prime (first stream)]
     (cons prime (sieve (filter 
                         (fn[x](not (zero? (mod x prime)))) 
                         (rest stream)))))))


(def primes (sieve (iterate inc 2)))

