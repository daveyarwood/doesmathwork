(ns doesmathwork.core
  (:require [ring.middleware.json :refer (wrap-json-response)]
            [ring.util.response   :refer (response)]
            [compojure.core       :refer :all]
            [compojure.handler    :as    handler]
            [compojure.route      :as    route]
            [cheshire.core        :as    json]
            [me.raynes.conch      :refer (with-programs)]))

(defn language-path
  [lang]
  (str "langs/" lang))

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
  (route/not-found "Not Found"))

(def app
  (-> (handler/site app-routes)
      wrap-json-response))
