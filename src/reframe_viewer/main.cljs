(ns reframe-viewer.main
  (:require [re-frame.core :as re]
            [re-frame.db :as db]
            [reframe-viewer.channel :as channel]
            [reframe-viewer.listeners :refer [listeners]]))

(defn init []
  (channel/set-recieve-message-listener listeners)
  (re/add-post-event-callback #(channel/send-message @db/app-db)))