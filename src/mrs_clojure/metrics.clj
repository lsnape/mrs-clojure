(ns mrs-clojure.metrics
  (:require [clojure.string :as str]
            [metrics.core :refer [default-registry]]
            [metrics.reporters.graphite :as graphite]
            [mount.core :refer [defstate]]
            [mrs-clojure.config :refer [config]]))

(defn- prefix
  [environment-name service-name box-id]
  (str/join "." [environment-name service-name box-id]))

(defn- remove-all-metrics!
  []
  (doseq [metric (.getNames default-registry)]
    (.remove default-registry metric)))

(defn start-graphite-reporting
  [{graphite-opts :graphite :as config}]
  (let [{:keys [enabled? post-interval-seconds]} graphite-opts
        reporter-opts (merge (select-keys graphite-opts [:host :port])
                             {:prefix (prefix (:environment-name config)
                                              (:service-name config)
                                              (:box-id config))})]
    (doto (graphite/reporter graphite-opts)
      #(when enabled?
         (graphite/start % post-interval-seconds)))))

(defstate graphite-reporter
  :start (start-graphite-reporting (config))
  :stop (do (graphite/stop graphite-reporter) 
            (remove-all-metrics!)))
