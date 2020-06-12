# `hmt-authlists`

This repository hosts CITE Collections defining authority lists for the Homer Multitext project.

Collections are cataloged in [CEX format](https://github.com/cite-architecture/citedx) in the file [catalog/catalog.cex](catalog/catalog.cex).

For each collection, a file in the [data](data) directory has the contents in a `cex` file.

## For users


If you need a new identifier added to one of the authority lists, please create a new issue ([the large green button on this page](https://github.com/homermultitext/hmt-authlists/issues)), using one of the preformatted templates.

If you need to look up the URN for a dingbat, you can use [this chart](https://github.com/homermultitext/hmt-authlists/blob/master/dingbatviewer.md).

## For editors

### Editing a collection

Collections should be edited in a local clone of this repository and validated before committing.

### Validating



From a terminal, open an sbt console:

    sbt console


Then, in the console, load the file `validate.sc`.  (Note the colon in  `:load` !)

    :load validate.sc

Follow the on-screen instructions to validate a collection (e.g., `validate("place")`).


### Format of personal names list

The list of personal names has 7 columns with these headings:

>urn#mf#character#label#description#status#redirect

For the `mf` column, values should be one of `m` for male, `f` female or `TBD` for any values not yet determined.

For the `character` column, values should be one of `literary` for figures appearing in the *Iliad* or other literary works, `historical` for historical figures such as editors or scholars, and `divinity` for names of divinities.

For the `status` column, values should be one of `proposed`, `accepted`, `rejected`.  
