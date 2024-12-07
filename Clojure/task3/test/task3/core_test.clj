(ns task3.core-test
  (:require [clojure.test :refer :all]
            [task3.core :refer :all]))

(deftest test-parallel-filter
  (testing "Простые тесты"
  (let [input (range 100)
        pred even?]
    (is (= (filter pred input)
           (parallel-filter pred input)))
    (is (= (filter pred []) (parallel-filter pred []))))))

(deftest test-pfilter-lazy
  (testing "Бесконечная последовательность"
    (let [infinite-seq (range)
          pred even?
          result (parallel-filter pred infinite-seq)]
      (is (= (take 10 result)
             (take 10 (filter pred infinite-seq)))))))


(deftest demo-performance
  (testing "Perfomance test"
  (let [large-seq (range 1500)
        pred #(< (num-divisors %) 1)]
    (println "Serial filter:")
    (time (doall (filter pred large-seq)))
    (println "Parallel filter:")
    (time (doall (parallel-filter pred large-seq))))))