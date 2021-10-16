(ns app.router
    (:require [reitit.frontend :as rf]
              [reagent.core :as r]
              [reitit.coercion.spec :as rss]
              [app.home.views :as home]
              [app.second-page.views :as second-page]))

(def state (r/atom nil))

(defn on-navigate
  [new-match]
  (reset! state new-match))

(def routes
  [["/" {:name home/route :view home/view}]
   ["/second-page" second-page/routes]])

(def router
  (rf/router routes {:data {:coercion rss/coercion}}))