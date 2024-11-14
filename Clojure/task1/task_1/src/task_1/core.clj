(ns task-1.core)

(defn add-char [char words]
  (map (fn [word] (str char word)) words))

(defn filter-duplicates [words]
  (remove (fn [word] (= (first word) (second word))) words))

(defn add-alphabet [words alphabet]
  (reduce concat (map (fn [x] (add-char x words)) alphabet)))

(defn add-n-filter [words alphabet]
  (filter-duplicates (add-alphabet words alphabet)))

(defn all-strings [alphabet length]
  (reduce
     (fn [words _] (add-n-filter words alphabet))
     alphabet
     (range (- length 1))))

(defn -main []
  (let [chars '("a" "b" "c" "d")
        length 4
        allStrings (all-strings chars length)]
    (println (count allStrings)))
  )