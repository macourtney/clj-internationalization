(ns clj-internationalization.term
  (:refer-clojure :only [])
  (:require [clj-internationalization.core :as core]))

(core/load-term-bundle (core/find-term-bundle "Terms") (clojure.core/ns-name clojure.core/*ns*))