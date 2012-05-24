(ns clj-internationalization.core
  (:import [java.util ResourceBundle Locale])
  (:require [clojure.tools.loading-utils :as loading-utils]
            [clojure.tools.string-utils :as string-utils]))

(def resource-name "Terms")

(defn
  parameterize [term params]
  (string-utils/str-replace-if
    (if (seq params)
      (reduce 
        string-utils/str-replace-pair
        term
        (map #(list (str "{" %1 "}") (str %2)) (iterate inc 0) params))
      term)
    { "{{}" "{", "{}}" "}"}))

(let [term-bundle (. ResourceBundle getBundle resource-name (. Locale getDefault))]
  (doseq [property-key (.keySet term-bundle)]
    (eval 
      (list 
        'defn 
        (symbol (loading-utils/underscores-to-dashes (.toLowerCase property-key))) 
        ['& 'params]
        (list 'parameterize (.getString term-bundle property-key) 'params)))))