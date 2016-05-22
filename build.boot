(set-env!
  :source-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.8.0"]
                  [pandeiro/boot-http  "0.7.3"]
                  [ring/ring-json      "0.4.0"]
                  [compojure           "1.5.0"]
                  [cheshire            "5.4.0"]
                  [me.raynes/conch     "0.8.0"]])

(require '[pandeiro.boot-http :as http])

(deftask serve
  []
  (comp
    (http/serve :handler 'doesmathwork.core/app :reload true)
    (wait)))
