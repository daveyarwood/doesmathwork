(set-env!
  :source-paths   #{"src/clj" "src/cljs"}
  :resource-paths #{"html"}
  :dependencies   '[[org.clojure/clojure       "1.8.0"]
                    [org.clojure/clojurescript "1.7.228"]
                    [pandeiro/boot-http        "0.7.3"]
                    [adzerk/boot-reload        "0.4.7"]
                    [adzerk/boot-cljs          "1.7.228-1"]
                    [adzerk/boot-cljs-repl     "0.3.0"]
                    [com.cemerick/piggieback   "0.2.1"]
                    [weasel                    "0.7.0"]
                    [org.clojure/tools.nrepl   "0.2.12"]
                    [ring/ring-json            "0.4.0"]
                    [compojure                 "1.5.0"]
                    [me.raynes/conch           "0.8.0"]])

(require '[pandeiro.boot-http    :as    http]
         '[adzerk.boot-cljs      :refer (cljs)]
         '[adzerk.boot-cljs-repl :refer (cljs-repl)]
         '[adzerk.boot-reload    :refer (reload)])

(deftask serve
  []
  (comp
    (http/serve :handler 'doesmathwork.core/app
                :resource-root "target"
                :reload true)
    (watch)
    (reload)
    (cljs-repl)
    (cljs)
    (target :dir #{"target"})))
