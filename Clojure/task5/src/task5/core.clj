(ns task5.core
  (:gen-class)
  ;; (:require [clojure.core.async :as async])
    )



(def transaction-attempts (atom 0))

(defn my-cycle [left-fork right-fork]
  (dosync
   (swap! transaction-attempts inc)
   (alter left-fork inc)
   (alter right-fork inc)
   (Thread/sleep 300))
  (Thread/sleep 300))

(defn philosopher-dining [fork-pair]
  (let [[left-fork right-fork] fork-pair]
    (try (my-cycle left-fork right-fork) (catch Exception e))))

(defn philosopher
  [fork-pair]
  (new Thread
       (fn []
         (Thread/sleep 200)
         (philosopher-dining fork-pair)
         (Thread/sleep 200)
         (recur))))

(defn start-philosophers
  [fork-refs]
  ;(println fork_refs)
  (doall (for [x (range (count fork-refs))]
           (.start (philosopher [(nth fork-refs x) (nth fork-refs (rem (+ x 1) (count fork-refs)))])))))

(defn -main [& args]
  (let [num-forks 5
        forks (vec (repeatedly num-forks #(ref 0)))]
    (start-philosophers forks)
    (.start (Thread.
             (fn []
               (loop []
                 (Thread/sleep 600)
                 (println "Restart count:" @transaction-attempts)
                 (println "Forks usage:" (map deref forks))
                 (recur)))))))