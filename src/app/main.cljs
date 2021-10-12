(ns app.main
  (:require [reagent.core :as r]
            [shared.view :as view]
            [app.router :as router]
            [reitit.frontend.easy :as rfe]))

(defn landing-page
  []
  (let [{{:keys [view name]} :data} @router/state]
    [:div
      "Hello world"
      name
      [:br]
      (if (= ::second-page name)
        [:div "Second page"]
        (view @router/state))]))


(defn ^:dev/after-load start
  []
  (rfe/start! router/router router/on-navigate {:use-fragment false})
  (r/render [landing-page]
            (.getElementById js/document "app")))



(defn ^:export main
  []
  (start))