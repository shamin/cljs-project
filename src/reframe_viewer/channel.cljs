(ns reframe-viewer.channel
  (:require [clojure.walk :as walk]))

(defn send-message [type data]
  (->> {:detail {:event type :data data}}
       (clj->js)
       (js/CustomEvent. "reframe-viewer-event")
       (js/window.dispatchEvent)))

(defn recieve-message-callback [listeners]
  (fn [data]
    (let [detail (walk/keywordize-keys (js->clj (.-detail data)))
          type (:event detail)
          listener (get listeners type)]
      (when listener 
        (listener detail)))))

(defn set-recieve-message-listener [listeners]
  (.addEventListener js/window "reframe-viewer-event-webpage" (recieve-message-callback listeners)))