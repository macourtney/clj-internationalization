clj-internationalization
========================

Another internationalization library for clojure. clj-internationalization reads a resource bundle and generates functions for each key.

## Usage

First add a properties file named Terms.properties to your classpath or resource path.

Add your terms to Terms.properties, for example:

    TEST_TERM = test term

To use the term, simply import the clj-internationalization.core and call the generated function for it:

    (require [clj-internationalization.core :as clj-i18n])
    
    (clj-i18n/test-term)

Clj-internationalization also supports parameterization. For example, the following parameterized term:

    TEST_PARAMETER = test {0} parameter

Can be passed a parameter like:

    (clj-i18n/test-parameter "blah")

Which results in the string:

    "test blah parameter"