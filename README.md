# `hmt-authlists`

This repository hosts CITE Collections in [CEX format](https://github.com/cite-architecture/citedx) defining authority lists for the Homer Multitext project. Collections are cataloged in CEX files in the `catalog` directory; datasets are in files in the `data` directory.

## For users

If you need a new identifier added to one of the authority lists, please create a new issue ([the large green button on this page](https://github.com/homermultitext/hmt-authlists/issues)), using one of the preformatted templates.

If you need to look up the URN for a dingbat, you can use [this chart](https://github.com/homermultitext/hmt-authlists/blob/master/dingbatviewer.md).

## For editors

### Editing and validating a collection

Collections should be edited in a local clone of this repository and validated before committing.  Before you start editing any files, open the 
Pluto notebook in this repository, `authlist-validator.jl`.  (That is, start Pluto from a Julia terminal, `using Pluto`, then `Pluto.run()`, and open `authlist-validator.jl`).  

From the popup menu in the notebook, choose the collection you want to edit in order to find the *highest numbered* URN used so far in your collection, Follow the instructions in the notebook to validate your work as you edit.


### Format of personal names list

The list of personal names has 7 columns with these headings:

>urn|mf|character|label|description|status|redirect

- For the `mf` column, values should be one of `m` for male, `f` female or `TBD` for any values not yet determined.
- For the `character` column, values should be one of `literary` for figures appearing in the *Iliad* or other literary works, `historical` for historical figures such as editors or scholars, and `divinity` for names of divinities.
- For the `status` column, values should be one of `proposed`, `accepted`, `rejected`.

