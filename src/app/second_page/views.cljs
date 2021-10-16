(ns app.second-page.views)

(def route ::second-page)
(def route-second ::second-page-2)

(defn view []
  [:p "Second Page"])

(defn view-second []
  [:p "Second Page 2"])

(def routes
  [["/first" {:name route :view view}]
   ["/second" {:name route-second :view view-second}]])