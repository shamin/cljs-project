(ns shared.core.c.button.button
  (:require [reagent.core :as r]))

(defn button [{:keys [on-click]} & contents]
  [:button {:on-click on-click} contents])