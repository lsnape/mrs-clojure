(ns user
  (:require [mount.core :as mount]
            [clojure.tools.namespace.repl :as tools-ns]
            [potemkin :refer [import-vars]]))

(tools-ns/disable-reload!)

(import-vars [mount.core start stop])

(defn go []
  (mount/start)
  :ready)

(defn refresh []
  (stop)
  (tools-ns/refresh))

(defn refresh-all []
  (stop)
  (tools-ns/refresh-all))

(defn reset []
  (stop)
  (tools-ns/refresh :after 'user/go))
