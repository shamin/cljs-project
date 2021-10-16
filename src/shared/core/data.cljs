(ns shared.core.data
  (:require [re-frame.core :as re]))

(re/reg-event-db
  :form-change
  (fn [db [_ form-path name new-value]]
    (assoc-in db (conj form-path name) new-value)))

(re/reg-sub
  :form-data
  (fn [db [_ form-path name]]
    (-> (get-in db form-path) 
                   (get name))))