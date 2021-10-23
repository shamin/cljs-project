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
  :todos/fetch-todos
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
        (re/dispatch [:todos/fetch-todos]))))


(def home-details-form-path [:home :details])

(re/reg-event-db
  ::submit-details-form
  (fn [db [_ result]]
    (println "Home details form data" (get-in db home-details-form-path))
    (-> db
      (assoc-in (conj home-details-form-path :submitting?) true))))













;; (def tickets-form-root [:tickets])
;; (def tickets-list-form-path (conj tickets-form-root :list))
;; (def new-ticket-form-path (conj tickets-form-root :new))

;; (re/reg-event-db
;;   ::fetch-tickets-success
;;   (fn [db [_ result]]
;;     (-> db
;;       (assoc-in (conj tickets-list-form-path :data) result)
;;       (assoc-in (conj tickets-list-form-path :loading?) false))))

;; (re/reg-event-db
;;   ::fetch-tickets-failure
;;   (fn [db [_ result]]
;;     (-> db
;;       (assoc-in (conj tickets-list-form-path :error) result)
;;       (assoc-in (conj tickets-list-form-path :loading?) false))))

;; (re/reg-event-fx
;;   ::fetch-tickets
;;   (fn [{:keys [db]} _]
;;     {:db   (-> db
;;                (assoc-in (conj tickets-list-form-path :loading?) true)
;;                (update-in tickets-list-form-path dissoc :data)
;;                (update-in tickets-list-form-path dissoc :error))
;;      :http-xhrio {:method          :get
;;                   :uri             "https://myapi.com/tickets"
;;                   :timeout         8000
;;                   :response-format (ajax/json-response-format {:keywords? true})
;;                   :on-success      [::fetch-tickets-success]
;;                   :on-failure      [::fetch-tickets-failure]}}))

;; (re/reg-sub ::tickets 
;;   (fn [db [_]]
;;     (or (get-in db tickets-list-form-path)
;;         (re/dispatch [::fetch-tickets]))))
