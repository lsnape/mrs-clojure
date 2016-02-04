(ns mrs-clojure.setup
  (:require [bidi.ring :refer [make-handler]]
            [aleph.http :refer [start-server]]
            [mount.core :refer [defstate] :as mount]
            [mrs-clojure.web :refer [routes]])
  (:gen-class))

(defstate web-server
  :start (start-server (make-handler #'routes) {:port 8081})
  :stop (.close web-server))

(defn -main
  [& args]
  (mount/start))
