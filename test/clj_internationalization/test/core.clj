(ns clj-internationalization.test.core
  (:use [clj-internationalization.core])
  (:use [clojure.test]))

(deftest test-term-test
  (is (= (test-term) "test term")))

(deftest test-parameter-test
  (is (= (test-parameter "blah") "test blah parameter")))
