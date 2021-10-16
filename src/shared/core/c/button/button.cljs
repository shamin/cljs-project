(ns shared.core.c.button.button
  (:require [reagent.core :as r]
            [re-frame.core :as re]))

(defn button [{:keys [form-path]}]
  (let [submitting?- (when form-path (re/subscribe [:form-data form-path :submitting?]))]
    (fn [{:keys [on-click selected? form-path loading?]} & contents]
      (let [loading? (or loading? (when submitting?- @submitting?-))
            on-click (if loading? #(.preventDefault %) on-click)]
        [:button {:on-click on-click
                  :style {:background (if selected? "#e67e22" "#fff")}} (if loading? "loading" contents)]))))