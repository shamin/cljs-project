(ns app.home.data
  (:require [re-frame.core :as re]
            [ajax.core :as ajax]))

(def todo-form-path [:todos])

(re/reg-event-db
  ::success-http-result
  (fn [db [_ result]]
    (-> db
      (assoc-in (conj todo-form-path :data) result)
      (assoc-in (conj todo-form-path :loading?) false))))

(re/reg-event-db
  ::failure-http-result
  (fn [db [_ result]]
    (-> db
      (assoc-in (conj todo-form-path :error) result)
      (assoc-in (conj todo-form-path :loading?) false))))

(re/reg-event-fx
  ::fetch-todos
  (fn [{:keys [db]} _]
    {:db   (-> db
               (assoc-in (conj todo-form-path :loading?) true)
               (update-in todo-form-path dissoc :data)
               (update-in todo-form-path dissoc :error))
     :http-xhrio {:method          :get
                  :uri             "https://jsonplaceholder.typicode.com/todos/1"
                  :timeout         8000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [::success-http-result]
                  :on-failure      [::failure-http-result]}}))

(re/reg-sub ::todos 
  (fn [db [_]]
    (or (get-in db todo-form-path)
        (re/dispatch [::fetch-todos]))))


(def home-details-form-path [:home :details])

(re/reg-event-db
  ::submit-details-form
  (fn [db [_ result]]
    (println "Home details form data" (get-in db home-details-form-path))
    (-> db
      (assoc-in (conj home-details-form-path :submitting?) true))))