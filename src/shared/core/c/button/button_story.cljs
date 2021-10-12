(ns shared.core.c.button.button_story
  (:require [reagent.core :as r]))

(defn button [{:keys [on-click]} & contents]
  [:button {:on-click on-click} contents])