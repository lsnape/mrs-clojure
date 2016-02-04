(ns mrs-clojure.web
  (:require [mrs-clojure.config :refer [config]]
            [yada.swagger :refer [swaggered]]
            [yada.yada :refer [yada resource content-type]]))

(def not-found-resource
  (yada.protocols/as-resource nil))

(def match-all
  true)

(def routes
  ["/"
   [["ping" (yada "pong")]
        
    ["healthcheck" (yada {:name "mrs-clojure"
                          :version (:version (config))
                          :success true
                          :dependencies []})]
        
    ["1.x" (swaggered {:info {:title "mrs-clojure swagger docs"
                              :version "0.0.1"
                              :description "A lengthy description"}
                       :basePath "/1.x"}
                      ["/about" (yada "This is a test")])]

    [match-all (yada not-found-resource)]]])
