(ns shared.core.c.button.button
  (:require [reagent.core :as r]))

(defn button [{:keys [on-click selected?]} & contents]
  [:button {:on-click on-click
            :style {:background (if selected? "#e67e22" "#c0392b")}} contents])