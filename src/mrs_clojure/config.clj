(ns mrs-clojure.config
  (:require [environ.core :refer [env]]
            [mrconfig.config :refer [config-filepath make-config-fn]]))

(def environ-config
  (let [service-port (some-> (env :service-port) Integer/valueOf)]
    (cond-> {:version (System/getProperty "mrs-clojure.version")}
      service-port (assoc {:service {:port service-port}}))))

(def config
  (make-config-fn (config-filepath) environ-config))
