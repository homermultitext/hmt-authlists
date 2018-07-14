import edu.holycross.shot.cite._
import scala.io.Source

val collectionsToFiles = Map(
 "place"   -> "hmtplaces.cex",
 "pers" ->   "hmtnames.cex",
  "astro" ->   "astronomy.cex",
  "work" ->   "citedworks.cex"
)

val collectionsToColumns= Map(
 "place"   -> 6,
 "pers" ->   5,
  "astro" ->   5,
  "work" ->   3
)



// Report on status of a collection.
def validate(collectionName: String): Unit = {
  if (collectionsToFiles.keySet.contains(collectionName)) {
    val lines = Source.fromFile("data/" + collectionsToFiles(collectionName)).getLines.toVector.filter(_.nonEmpty)
    val colSize = collectionsToColumns(collectionName)
    val urns: Vector[Cite2Urn] = lines.drop(2).map(l => {
      val cols = l.split("#")
      try {
        Cite2Urn(cols(0))

      } catch {
        case t: Throwable => {
          println("Failed at " + cols(0) + " from " + l)
          throw(t)
        }
      }

    })
    // check for dupe ids.
    val dupes = urns.groupBy( u => u).toVector.map({ case (k,v) => (k, v.size) }).filter(_._2 > 1)

    for (l <- lines.drop(2)) {
      val cols = l.split("#")
      val oneDown = colSize - 1
      try {
        val lineOk = if (cols.size == colSize) {
          true
        } else if (cols.size == oneDown) {
          if (l.last == '#') {
            true
          } else {
            println("Failed at line "  + l)
            println("Missing final '#'")
            println("Number of columns: " + cols.size + "\n")
            throw(new Exception("Missing final '#'"))
          }
        } else {
          println("Failed at line "  + l)
          println("Wrong number of columns: " + cols.size + "\n")
          throw(new Exception("Wrong number of columns: " + cols.size))
        }
      } catch {
        case t: Throwable => {
          println("Failed at line " + l)
          throw(t)
        }
      }
    }




    val numsOnly = urns.map(_.objectComponent.replaceFirst(collectionName,"").toInt).sorted.reverse
    println(s"${collectionsToFiles(collectionName)}: ${urns.size} entries with valid URNs.")
    if (dupes.nonEmpty) {
      println("\nERROR:  there were duplicate IDs:")
      for (dupe <- dupes) {
        println("\t" + dupe._1 + " -- " + dupe._2 + " entries.")
      }
      println("\n")
    }
    println(s"Highest value = ${collectionName}${numsOnly(0)}")

  } else {
      println("Usage: validate(COLLECTION)")
      println("COLLECTION should be one of:\n")
      println("\tplace")
      println("\tpers")
  }
}


println("\n\nValidate a collection and find current highest number:")
println("\n\tvalidate(COLLECTION)")
println("\nCOLLECTION should be one of:\n")
for (auth <- collectionsToFiles.keySet) {
  val f = collectionsToFiles(auth)
  println("\t\"" + auth + "\" (for " + f + ")")
}
