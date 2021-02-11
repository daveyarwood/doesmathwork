(set-env!
  :source-paths   #{"src/clj" "src/cljs"}
  :resource-paths #{"html" "css"}
  :dependencies   '[[org.clojure/clojure       "1.10.2"]
                    [org.clojure/clojurescript "1.10.773"]
                    [pandeiro/boot-http        "0.8.3"]
                    [adzerk/boot-reload        "0.6.0"]
                    [adzerk/boot-cljs          "2.1.5"]
                    [adzerk/boot-cljs-repl     "0.4.0"]
                    [cider/piggieback          "0.5.2" :scope "test"]
                    [nrepl                     "0.8.3" :scope "test"]
                    [weasel                    "0.7.1"]
                    [org.clojure/tools.nrepl   "0.2.13"]

                    ; back end
                    [ring/ring-core            "1.9.0"]
                    [ring/ring-json            "0.5.0"]
                    [ring-cors/ring-cors       "0.1.13"]
                    [compojure                 "1.6.2"]
                    [me.raynes/conch           "0.8.0"]

                    ; front end
                    [domina                    "1.0.3"]
                    [hiccups                   "0.3.0"]
                    [cljs-ajax                 "0.8.1"]])

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
