(ns clj-internationalization.test.core
  (:use [clj-internationalization.core])
  (:use [clojure.test])
  (:import [java.util MissingResourceException]))

(deftest test-find-term-bundle
  (is (find-term-bundle))
  (try
    (find-term-bundle "fail")
    (is false "Expected an exception when trying to load a missing term bundle.")
    (catch MissingResourceException e
      (comment "This test passed."))))

(deftest test-parameterize
  (is (= (parameterize "blah" []) "blah"))
  (is (= (parameterize "foo {0}" ["bar"]) "foo bar"))
  (is (= (parameterize "foo {0} baz {1}" ["bar" "biz"]) "foo bar baz biz"))
  (is (= (parameterize "{0} bar {1} biz" ["foo" "baz"]) "foo bar baz biz")))