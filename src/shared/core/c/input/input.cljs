(ns shared.core.c.input.input
  (:require [reagent.core :as r]
            [re-frame.core :as re]
            [shared.core.data]))

(defn input [{:keys [form-path name value on-change]}]
  (let [value (if (and form-path name)
                @(re/subscribe [:form-data form-path name])
                value)
        on-change (fn [e]
                    (let [new-value (.. e -target -value)]
                      (if (and form-path name)
                        (re/dispatch [:form-change form-path name new-value]))
                      (when on-change (on-change e))))]
    [:input {:value (or value "")
             :on-change on-change}]))