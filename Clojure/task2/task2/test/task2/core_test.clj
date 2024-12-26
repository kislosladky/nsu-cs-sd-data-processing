(ns task2.core-test
  (:require [clojure.test :refer :all]
            [task2.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 0))))


(deftest test-primes
  (testing "First few primes"
    (is (= (take 10 primes) [2 3 5 7 11 13 17 19 23 29])))
  (testing "Nth prime"
    (is (= (nth primes 0) 2))
    (is (= (nth primes 5) 13))
    (is (= (nth primes 9) 29)))
  (testing "Large prime (sanity check)"
    (is (= (nth primes 99) 541))))