(ns mrs-clojure.setup
  (:require [aleph.http :refer [start-server]]
            [bidi.ring :refer [make-handler]]
            [mount.core :refer [defstate] :as mount]
            [mrs-clojure.web :refer [routes]])
  (:gen-class))

(defstate web-server
  :start (start-server (make-handler routes) {:port 8080})
  :stop (.close web-server))

(defn -main
  [& args]
  (mount/start))
