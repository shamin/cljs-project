(ns app.router
    (:require [reitit.frontend :as rf]
              [reagent.core :as r]
              [reitit.coercion.spec :as rss]
              [shared.view :as views]))

(def state (r/atom nil))

(defn on-navigate
  [new-match]
  (reset! state new-match))

(def routes
  [["/" {:name ::homepage :view views/page}]
   ["/second-page" {:name ::second-page :view views/second-page}]])

(def router
  (rf/router routes {:data {:coercion rss/coercion}}))