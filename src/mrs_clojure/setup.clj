(ns mrs-clojure.setup
  (:require [aleph.http :refer [start-server]]
            [bidi.ring :refer [make-handler]]
            [metrics.ring.expose :refer [expose-metrics-as-json]]
            [mount.core :refer [defstate] :as mount]
            [mrs-clojure.config :refer [config]]
            [mrs-clojure.middleware :refer [wrap-aleph-metrics]]
            [mrs-clojure.web :refer [routes]])
  (:gen-class))

(def handler
  (-> (make-handler routes)
      (wrap-aleph-metrics)
      (expose-metrics-as-json)))

(defstate web-server
  :start (start-server (make-handler handler) (:service (config)))
  :stop (.close web-server))

(defn -main
  [& args]
  (mount/start))
