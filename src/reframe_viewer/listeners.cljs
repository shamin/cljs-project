(ns reframe-viewer.listeners
  (:require [reframe-viewer.channel :as channel]
            [re-frame.db :as db]
            [re-frame.core :as re]
            [cljs.reader :as reader]))

(defn send-db []
  (channel/send-message "refreshDb" @db/app-db))

(defn dispatch-event [d]
  (println "Message dispatch-event" (:reframe-event d))
  (re/dispatch (reader/read-string (:reframe-event d))))

(def listeners {"get-db"         send-db
                "handle-event" dispatch-event})