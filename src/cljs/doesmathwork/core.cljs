(ns doesmathwork.core
  (:require [ajax.core  :as ajax]
            ;; [domina     :as dom]
            ;; [domina.css :as css]
            [hiccups.runtime])
  (:require-macros [hiccups.core :refer (html)]))

(defn lang-div-id
  [lang]
  (str "lang-" lang))

;; FIXME: The Domina library seems to have completely bit-rotted. Simply
;; requiring it causes a bunch of warnings and a fatal error to be printed:
;;
;; WARNING: domina is a single segment namespace at line 1 /home/dave/.config/boot/cache/tmp/tmp/doesmathwork/g5c/7hzrjy/main.out/domina.cljs
;; WARNING: Protocol DomContent is overwriting function nodes in file /home/dave/.config/boot/cache/tmp/tmp/doesmathwork/g5c/7hzrjy/main.out/domina.cljs
;; WARNING: Protocol DomContent is overwriting function single-node in file /home/dave/.config/boot/cache/tmp/tmp/doesmathwork/g5c/7hzrjy/main.out/domina.cljs
;; WARNING: *debug* not declared dynamic and thus is not dynamically rebindable, but its name suggests otherwise. Please either indicate ^:dynamic *debug* or change the name at line 111 /home/dave/.config/boot/cache/tmp/tmp/doesmathwork/g5c/7hzrjy/main.out/domina.cljs
;; No such namespace: goog.dom.query, could not locate goog/dom/query.cljs, goog/dom/query.cljc, or JavaScript source providing "goog.dom.query" in file /home/dave/.config/boot/cache/tmp/tmp/doesmathwork/g5c/7hzrjy/main.out/domina/css.cljs
;;
;; And then the ClojureScript compiler bombs out entirely and doesn't produce my
;; main.js.
;;
;; TODO: If/when I ever want to get this working again, a quick fix might be to
;; replace Domina with Dommy: https://github.com/plumatic/dommy

;; (defn get-result!
;;   [lang]
;;   (let [result-span (css/sel (str "#" (lang-div-id lang) " .result"))]
;;     (dom/add-class! result-span "loading")
;;     (dom/set-text! result-span "loading...")
;;     (ajax/GET (str "/test/" lang)
;;               {:handler #(do
;;                            (dom/remove-class! result-span "loading")
;;                            (let [answer (if (get % "result") "yes" "no")]
;;                              (dom/set-text! result-span answer)))})))

(defn init-langs
  [langs]
  (doseq [lang langs]
    (js/console.log lang)))
    ;; (dom/append! (dom/by-id "langs")
    ;;              (html [:div.lang {:id (lang-div-id lang)}
    ;;                     [:span.name [:strong lang] "? "]
    ;;                     [:span.result]]))
    ;; (get-result! lang)))

(defn init
  []
  (ajax/GET "/list" {:handler init-langs}))

(set! (.-onload js/window) init)
