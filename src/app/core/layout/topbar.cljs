(ns app.core.layout.topbar
  (:require [reitit.frontend.easy :as rfe]
            [app.home.views :as home]
            [app.second-page.views :as second-page]
            [shared.core.components :as c]))

(defn topbar-item [{:keys [route name current-page]}]
  [c/button {:on-click #(rfe/push-state route)
             :selected? (= current-page route)} name])

(defn topbar [{:keys [current-page]}]
  [:div {:style {:background :tomato
                     :padding 10
                     :display :flex
                     :align-items :center}} 
        [:p "Top bar"]
        [:div {:style {:margin-left 20}}]
        [topbar-item {:route home/route
                      :name "Home"
                      :current-page current-page}]
        [topbar-item {:route second-page/route
                      :name "Second Page"
                      :current-page current-page}]
        [topbar-item {:route second-page/route-second
                      :name "Second Page 2"
                      :current-page current-page}]])