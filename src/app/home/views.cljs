(ns app.home.views
  (:require [app.home.data :as data]
            [re-frame.core :as re]
            [shared.core.components :as c]))

(def route ::homepage)

(defn view []
  (let [todos (re/subscribe [::data/todos])]
    [:div
      [:p "Welcome Home" (str @todos)]
      [c/button {:on-click #(re/dispatch [:todos/fetch-todos])} "Reload"]
      [:br]
      [:br]
      [c/input {:form-path data/home-details-form-path :name :fullname}]
      [c/button {:form-path data/home-details-form-path :on-click #(re/dispatch [::data/submit-details-form])} "Submit"]]))
