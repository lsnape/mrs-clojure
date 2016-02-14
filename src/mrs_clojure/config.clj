(ns mrs-clojure.config
  (:require [clojure.string :as str]
            [clojure.walk :as walk]
            [environ.core :refer [env]]
            [mount.core :refer [defstate]]
            [mrconfig.config :refer [make-config-fn]])
  (:import [java.net InetAddress UnknownHostException]))

(def ^:private config-path
  (env :app-config-path "application-config.edn"))

(def ^:private hostname
  (try
    (-> (InetAddress/getLocalHost)
        .getHostName
        (str/split #"\.")
        first)
    (catch UnknownHostException e
      "unknown")))

(def ^:private production?
  (Boolean/valueOf (env :production "false")))

(def ^:private service-port
  (some-> (env :service-port) Integer/valueOf))

(def ^:private version
  (System/getProperty "mrs-clojure.version"))

(def env-config
  {:box-id (env :box-id)
   :environment-name (env :environment-name)
   :hostname hostname
   :service {:name (env :service-name)
             :port service-port}
   :version version})

(defstate config
  :start (make-config-fn config-path {:env-config env-config
                                      :cached? production?}))
