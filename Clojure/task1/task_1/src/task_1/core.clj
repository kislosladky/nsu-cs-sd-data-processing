(ns task-1.core)

; добавляю символ в начало каждого слова
(defn add-char [char words]
  (map (fn [word] (str char word)) words))

; отбрасываю слова, у которых 2 первых символа совпадают
(defn filter-duplicates [words]
  (remove (fn [word] (= (first word) (second word))) words))

; объединяю подсписки в один список
(defn add-alphabet [words alphabet]
  (reduce concat (map (fn [x] (add-char x words)) alphabet)))

; последовательно применяю 2 функции, описанных выше
(defn add-n-filter [words alphabet]
  (filter-duplicates (add-alphabet words alphabet)))

; полное решение. Использую reduce вместо цикла и игнорирую значения, по которым идет итерация
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