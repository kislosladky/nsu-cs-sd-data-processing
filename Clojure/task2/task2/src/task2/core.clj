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

;; (defn -main []
;;   (let ))

;; Unit tests
(deftest test-primes
  (testing "First few primes"
    (is (= (take 10 primes) [2 3 5 7 11 13 17 19 23 29])))
  (testing "Nth prime"
    (is (= (nth primes 0) 2))
    (is (= (nth primes 5) 13))
    (is (= (nth primes 9) 29)))
  (testing "Large prime (sanity check)"
    (is (= (nth primes 99) 541))))

;; To run tests:
;; (run-tests)

