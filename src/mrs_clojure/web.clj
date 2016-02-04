(ns mrs-clojure.web
  (:require [yada.yada :refer [yada resource content-type]]))

(def version
  "1.0.0")

(def not-found-resource
  (yada.protocols/as-resource nil))

(def routes
  ["/" [["ping" (yada "pong")]
        ["healthcheck" (yada {:name "mrs-clojure"
                              :version version
                              :success true
                              :dependencies []})]
        [true not-found-resource]]])
