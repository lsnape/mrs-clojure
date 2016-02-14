(defproject mrs-clojure "0.1.0-SNAPSHOT"
  :description "Experiment with bleeding edge libraries that are candidate for a new MixRadio template"
  :url "https://github.brislabs.com/lsnape/mrs-clojure"
  
  :dependencies [[aleph "0.4.1-beta2"]
                 [bidi "1.25.0"]
                 [environ "1.0.2"]
                 [manifold "0.1.2-alpha2"]
                 [medley "0.7.1"]
                 [mount "0.1.9"]
                 [mrconfig "0.1.3"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/core.cache "0.6.4"]
                 [org.clojure/tools.logging "0.3.1"]
                 [yada "1.1.0-20160202.093502-16"]]
  
  :profiles {:dev {:source-paths ["dev" "dev/clj"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [potemkin "0.4.3"]]
                   :repl-options {:init-ns user}}}

  :repositories {"internal-clojars" "http://clojars.brislabs.com/repo"}

  :resource-paths ["resources"])
