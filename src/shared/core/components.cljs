(ns shared.core.components
  (:require [reagent.core :as r]
            [shared.core.c.button.button]
            [shared.core.c.input.input]))

(def button shared.core.c.button.button/button)
(def input shared.core.c.input.input/input)
