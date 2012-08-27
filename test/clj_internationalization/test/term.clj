(ns clj-internationalization.test.term
  (:require [clj-internationalization.term :as term])
  (:use [clojure.test])
  (:import [java.util MissingResourceException]))

(deftest test-term-test
  (is (= (term/test-term) "test term"))
  ; Verify there are no symbol collisions.
  (is (= (term/find) "find")))

(deftest test-parameter-test
  (is (= (term/test-parameter "blah") "test blah parameter")))
