clj-internationalization
========================

Another internationalization library for clojure. clj-internationalization reads a resource bundle and generates functions for each key.

## Usage

First add a properties file named Terms.properties to your classpath or resource path.

Add your terms to Terms.properties, for example:

    TEST_TERM = test term

To use the term, simply import the clj-internationalization.core and call the generated function for it:

    (require [clj-internationalization.term :as term])
    
    (term/test-term)

Clj-internationalization also supports parameterization. For example, the following parameterized term:

    TEST_PARAMETER = test {0} parameter

Can be passed a parameter like:

    (term/test-parameter "blah")

Which results in the string:

    "test blah parameter"

## Creating your own terms namespace

If you want to use your own terms namespace, or want to use a properties file other than Terms.properties file, you will
need to create your own namespace in the following way:

Assuming you want to use the namespace, my-terms and the bundle MyTerms.properties, start by creating a my_terms.clj
file in the appropriate place in your project.

Your ns description should look like:

    (ns my-terms
      (:refer-clojure :only [])
      (:require [clj-internationalization.core :as core]))

The above ns call removes all clojure core symbols from the namespace and requires the clj-internationalization.core
namespace. The clojure core symbols were removed to keep name collisions from occurring. You'll need
clj-internationalization.core to load your properties file into your namespace.

All you need to do now is load the properties file with the following command:

    (core/load-terms "MyTerms")

The above function call finds the properties file for MyTerms and loads all of the term functions in to the current
namespace.

Your full my_terms.clj file should look like:

    (ns my-terms
      (:refer-clojure :only [])
      (:require [clj-internationalization.core :as core]))
    
    (core/load-terms "MyTerms")