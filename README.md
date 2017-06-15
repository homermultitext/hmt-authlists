# `hmt-authlists`

This repository hosts CITE Collections defining authority lists for the Homer Multitext project.

Collections are cataloged in [CEX format](https://github.com/cite-architecture/citedx) in the file [catalog/catalog.cex](catalog/catalog.cex).

For each collection, a file in the [data](data) directory has the contents in a `csv` file.

## Utility script

You can use the included scala script to find the highest numbered identifier used in each collection.  Requires [scala](http://www.scala-lang.org/) and [sbt](http://www.scala-sbt.org/).  (If you're using the 2017 HMT project editor's VM, these are already installed.)


Start by opening an sbt console:


    sbt console


Then, in the console, load the file `count.sc`.  (Note the colon in  `:load` !)


    :load count.sc

    
