(ns task3.core
  (:gen-class))


(defn filter-chunk 
  "Фильтрация фрагмента последовательности"
  [pred chunk]
  (future (doall (filter pred chunk))))


(defn compute-and-concat-futures
  "Рекурсивно собирает результат"
  [processed-results remaining-futures pred]
  (if-let [remaining (seq remaining-futures)]

    (lazy-seq (lazy-cat (deref (first processed-results))
                        (compute-and-concat-futures (rest processed-results)
                                                    (rest remaining) pred)))
    (flatten (concat (map deref processed-results)))
  )
)


(defn parallel-filter
  "Реализация параллельного фильтра"
  ([pred sequence]
   (let [chunk-number (.availableProcessors (Runtime/getRuntime))
         chunk-size 60
         parts (map doall (partition-all chunk-size sequence))
         pool (map #(filter-chunk pred %) parts)]

     (compute-and-concat-futures pool (drop chunk-number pool) pred)))
)            


(defn num-divisors 
  "Используется для теста"
  [x]
  (count (filter (comp zero? (partial rem x)) (range 1 (inc x))))
)



