(ns shared.view
  (:require [reagent.core :as r]
            [shared.core.components :as c]
            [reitit.frontend.easy :as rfe]))

(defn page []
  [c/button {:on-click #(rfe/push-state :app.router/second-page)} "Hello world"])

(defn second-page []
  [:div "Second Page"])