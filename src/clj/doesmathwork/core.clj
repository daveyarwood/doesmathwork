(ns doesmathwork.core
  (:require [clojure.string       :as    str]
            [ring.middleware.json :refer (wrap-json-response)]
            [ring.util.response   :refer (response)]
            [compojure.core       :refer :all]
            [compojure.handler    :as    handler]
            [compojure.route      :as    route]
            [cheshire.core        :as    json]
            [me.raynes.conch      :refer (with-programs)]))

(def LANGS-DIR "langs")

(defn list-languages
  []
  (with-programs [ls]
    (str/split (ls LANGS-DIR) #"\n")))

(defn language-path
  [lang]
  (str LANGS-DIR "/" lang))

(defn language-implemented?
  [lang]
  (with-programs [ls]
    (try
      (ls (language-path lang))
      true
      (catch Throwable e
        false))))

(defn test-language
  [lang]
  (with-programs [make]
    (make "-s" "-C" (language-path lang) "run")))

(defroutes app-routes
  (GET "/list" []
    (response (list-languages)))
  (GET "/test/:lang" [lang]
    (if (language-implemented? lang)
      (try
        (let [output (test-language lang)]
          (response {:result true
                     :output output}))
        (catch Throwable e
          (response {:result false
                     :output (apply str ((juxt :stdout :stderr) (ex-data e)))})))
      {:status 404
       :body   (format "Language '%s' not implemented." lang)}))
  (route/files "/" {:root "target"})
  (route/resources "/" {:root "target"})
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      wrap-json-response))
