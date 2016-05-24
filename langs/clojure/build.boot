(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.8.0"]])

(require 'doesmathwork.core)

(deftask run
  []
  ((resolve 'doesmathwork.core/-main)))
