(ns mrs-clojure.config
  (:require [clojure.string :as str]
            [environ.core :refer [env]]
            [mount.core :refer [defstate]]
            [mrconfig.config :refer [make-config-fn]])
  (:import [java.net InetAddress UnknownHostException]))

(def config-path
  (env :app-config-path "application-config.edn"))

(def environment-name
  (env :environment-name))

(def hostname
  (try
    (-> (InetAddress/getLocalHost)
        .getHostName
        (str/split #"\.")
        first)
    (catch UnknownHostException e
      "unknown")))

(def production?
  (some-> (env :production "false") Boolean.))

(def service-port
  (some-> (env :service-port) Integer.))

(def version
  (System/getProperty "mrs-clojure.version"))

(def env-config
  {:box-id (env :box-id)
   :environment-name environment-name
   :hostname hostname
   :service {:name (env :service-name)
             :port service-port}
   :version version})

(defstate config
  :start (make-config-fn config-path
                         {:env-config env-config
                          :cached? production?
                          :profile environment-name}))
