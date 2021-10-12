(ns app.main
  (:require [reagent.core :as r]
            [shared.view :as view]
            [app.router :as router]
            [reitit.frontend.easy :as rfe]
            [app.home.views :as home]
            [app.second-page.views :as second-page]
            [app.core.layout.topbar :as topbar]))

(defn landing-page
  []
  (let [{{:keys [view name]} :data} @router/state]
    [:div
      [topbar/topbar {:current-page name}]
      [:br]
      (view @router/state)]))


(defn ^:dev/after-load start
  []
  (rfe/start! router/router router/on-navigate {:use-fragment false})
  (r/render [landing-page]
            (.getElementById js/document "app")))



(defn ^:export main
  []
  (start))