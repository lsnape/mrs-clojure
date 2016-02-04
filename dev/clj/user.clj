(ns user
  (:require [mount.core :as mount]
            [clojure.tools.namespace.repl :as tools-ns]
            [potemkin :refer [import-vars]]))

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
  (refresh :after 'user/go))

