(ns task-1.core)

(defn add-char [char words]
  (map (fn [word] (str char word)) words))

(defn filter-duplicates [words]
  (filter (fn [word] (= (count (set word)) (count word))) words))

(defn add-alphabet [words alphabet]
  (reduce concat (map (fn [x] (add-char x words)) alphabet)))

(defn add-n-filter [words alphabet]
  (filter-duplicates (add-alphabet words alphabet)))

(defn all-strings [alphabet times]
  (reduce
     (fn [words _] (add-n-filter words alphabet))
     alphabet
     (range (- times 1))))
