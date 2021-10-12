(ns app.home.views
  (:require [app.home.data :as data]
            [re-frame.core :as re]
            [shared.core.components :as c]))

(def route ::homepage)

(defn view []
  (let [todos (re/subscribe [::data/todos])]
    [:div
      [:p "Welcome Home" (str @todos)]
      [c/button {:on-click #(re/dispatch [::data/fetch-todos])} "Reload"]]))
