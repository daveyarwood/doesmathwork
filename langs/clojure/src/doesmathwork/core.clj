(ns doesmathwork.core)

(defn math-works
  []
  (= (+ 1 1) 2))

(defn -main
  []
  (println "[clojure] Checking to see if math works...")
  (if (math-works)
    (do
      (println "[clojure] Math works.")
      (System/exit 0))
    (do
      (println "[clojure] ERROR: Math doesn't work.")
      (System/exit 1))))
