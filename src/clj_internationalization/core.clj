(ns clj-internationalization.core
  (:import [java.util ResourceBundle Locale])
  (:require [clojure.tools.loading-utils :as loading-utils]
            [clojure.tools.string-utils :as string-utils]))

(def resource-name "Terms")

(def term-namespace-symbol 'clj-internationalization.term)

(defn parameterize
  "fill in the parameters of the given term. If given a bundle and key, then pull the term from the bundle,
parameterize, then return the result."
  ([term-bundle property-key params]
    (parameterize (.getString term-bundle property-key) params))
  ([term params]
    (string-utils/str-replace-if
      (if (seq params)
        (reduce 
          string-utils/str-replace-pair
          term
          (map #(list (str "{" %1 "}") (str %2)) (iterate inc 0) params))
        term)
      { "{{}" "{", "{}}" "}"})))

(defn find-term-bundle
  ([] (find-term-bundle resource-name))
  ([bundle-name]
    (ResourceBundle/getBundle bundle-name (Locale/getDefault))))

(defn convert-term-to-fn-symbol
  "Converts the given term to a symbol for use as the name of the term function."
  [term]
  (symbol (loading-utils/underscores-to-dashes (.toLowerCase (name term)))))

(defmacro defterm [term value]
  `(defn ~term [& params#]
    (parameterize ~value params#)))

(defn load-term [term value]
  (eval `(defterm ~(convert-term-to-fn-symbol term) ~value)))

(defn load-term-bundle
  ([] (load-term-bundle (find-term-bundle)))
  ([term-bundle]
    (load-term-bundle term-bundle term-namespace-symbol))
  ([term-bundle term-namespace]
    (let [current-ns *ns*]
      (in-ns term-namespace)
      (doseq [term (.keySet term-bundle)]
        (load-term term (.getString term-bundle term)))
      (in-ns (ns-name current-ns)))))

(defmacro load-terms
  "Loads the terms from the given bundle into the current namespace. If the bundle name is not given, then \"Terms\" is
used."
  ([] (load-terms resource-name))
  ([bundle-name]
    (let [current-ns (ns-name *ns*)]
      (load-term-bundle (find-term-bundle bundle-name) current-ns))))