(ns mrs-clojure.config
  (:require [clojure.walk :as walk]
            [environ.core :refer [env]]
            [mount.core :refer [defstate]]
            [mrconfig.config :refer [make-config-fn]]))

(def ^:private config-path
  (env :app-config-path "application-config.edn"))

(def ^:private production?
  (Boolean/valueOf (env :production "false")))

(def ^:private service-port
  (some-> (env :service-port) Integer/valueOf))

(def ^:private version
  (System/getProperty "mrs-clojure.version"))

(def env-config
  {:service {:port service-port}
   :version version})

(defstate config
  :start (make-config-fn config-path
                         {:env-config env-config
                          :cached? production?}))
