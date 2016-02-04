(ns mrs-clojure.web
  (:require [yada.swagger :refer [swaggered]]
            [yada.yada :refer [yada resource content-type]]))

(def version
  "1.0.0")

(def not-found-resource
  (yada.protocols/as-resource nil))

(def match-not-found
  true)

(def routes
  ["/"
   [["ping" (yada "pong")]
        
    ["healthcheck" (yada {:name "mrs-clojure"
                          :version version
                          :success true
                          :dependencies []})]
        
    ["1.x" (swaggered {:info {:title "mrs-clojure swagger docs"
                              :version "0.0.1"
                              :description "A lengthy description"}
                       :basePath "/1.x"}
                      ["/about" (yada "This is a test")])]]
   
   [match-not-found (yada not-found-resource)]])
