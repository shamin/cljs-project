(ns reframe-viewer.main
  (:require [re-frame.core :as re]
            [re-frame.db :as db]
            [reframe-viewer.channel :as channel]
            [reframe-viewer.listeners :refer [listeners]]))

(defonce prev-db (atom {}))

(defn handle-events [event]
  (let [[event-name & event-data] event
        db @db/app-db
        data {:event (str event-name)
              :event-data event-data
              :db db
              :before @prev-db
              :after db
              :timestamp (.now js/Date)
              :full-event (str event)}]
    (channel/send-message "newEvent" data)
    (reset! prev-db db)))

(defn init []
  (channel/set-recieve-message-listener listeners)
  (re/add-post-event-callback handle-events))