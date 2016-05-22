(ns doesmathwork.core
  (:require [ajax.core  :as ajax]
            [domina     :as dom]
            [domina.css :as css]
            [hiccups.runtime])
  (:require-macros [hiccups.core :refer (html)]))

(defn lang-div-id
  [lang]
  (str "lang-" lang))

(defn get-result!
  [lang]
  (let [result-span (css/sel (str "#" (lang-div-id lang) " .result"))]
    (dom/add-class! result-span "loading")
    (dom/set-text! result-span "loading...")
    (ajax/GET (str "/test/" lang)
              {:handler #(do
                           (dom/remove-class! result-span "loading")
                           (let [answer (if (get % "result") "yes" "no")]
                             (dom/set-text! result-span answer)))})))

(defn init-langs
  [langs]
  (doseq [lang langs]
    (dom/append! (dom/by-id "langs")
                 (html [:div.lang {:id (lang-div-id lang)}
                        [:span.name [:strong lang] "? "]
                        [:span.result]]))
    (get-result! lang)))

(defn init
  []
  (ajax/GET "/list" {:handler init-langs}))

(set! (.-onload js/window) init)
