import edu.holycross.shot.cite._
import scala.io.Source


val vocab = Set(
  "accepted", "proposed", "rejected"
)

val collectionsToFiles = Map(
 "place"   -> "hmtplaces.cex",
 "pers" ->   "hmtnames.cex",
 "astro" ->   "astronomy.cex",
 "work" ->   "citedworks.cex"
)

val collectionsToColumns = Map(
 "place"   -> 6,
 "pers" ->   7,
  "astro" ->   5,
  "work" ->   5
)

def checkColumnStructure (lns:  Vector[String], colSize: Int) = {
  for (l <- lns) {
    val cols = l.split("#")
    val oneDown = colSize - 1
    try {
      val lineOk = if (cols.size == colSize) {
        true
      } else if (cols.size == oneDown) {
        if (l.last == '#') {
          true
        } else {
          println("==>Failed at line "  + l)
          println("Missing final '#'")
          println("Number of columns: " + cols.size + "\n")
          println("\n")
          throw(new Exception("Missing final '#'"))
        }
      } else {
        println("==>Failed at line "  + l)
        println("Wrong number of columns: " + cols.size + "\n")
        println("\n")
        throw(new Exception("Wrong number of columns: " + cols.size))
      }
    } catch {
      case t: Throwable => {
        println("==>Failed at line " + l)
        println("\n")
        throw(t)
      }
    }
  }
}

def urnsFromLines(lines: Vector[String]) :  Vector[Cite2Urn] = {
  val urns = lines.map(l => {
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
  urns
}




val mfValues = Set("m", "f", "TBD")
val characterValues = Set("literary", "historical", "divinity", "TBD")

def checkPersNames(lines:  Vector[String]): Unit = {

  for (l <- lines) {
    val cols = l.split("#")
    if (! mfValues.contains(cols(1))) {
        val ex = "==>Invalid value for MF: " + cols(1) + " from " + l
        println(ex + "\n\n")
        throw(new Exception(ex))
    }
    if (! characterValues.contains(cols(2))) {
        val ex = "Invalid value for character type: " + cols(2) + " from " + l
        println(ex)
        throw(new Exception(ex))
    }
  }
}


// Report on status of a collection.
def validate(collectionName: String): Unit = {
  if (collectionsToFiles.keySet.contains(collectionName)) {
    // read data lines, dropping first 2 header lines
    val lines = Source.fromFile("data/" + collectionsToFiles(collectionName)).getLines.toVector.filter(_.nonEmpty).drop(2)
    val colSize = collectionsToColumns(collectionName)

    // Check that URNs are syntactically valid
    val urns = urnsFromLines(lines)

    // check for dupe ids.
    val dupes = urns.groupBy( u => u).toVector.map({ case (k,v) => (k, v.size) }).filter(_._2 > 1)
    val numsOnly = urns.map(_.objectComponent.replaceFirst(collectionName,"").toInt).sorted.reverse
    println(s"${collectionsToFiles(collectionName)}: ${urns.size} entries with valid URNs.")

    if (dupes.nonEmpty) {
      println("\nERROR:  there were duplicate IDs:")
      for (dupe <- dupes) {
        println("\t" + dupe._1 + " -- " + dupe._2 + " entries.")
      }
      println("\n")
    }

    val x = checkColumnStructure(lines, colSize)
    // check column structure

    if (collectionName == "pers") { checkPersNames(lines) }

    // check status value
    for (l <- lines) {
      val cols = l.split("#")
      val status = cols(colSize - 2)
      require(vocab.contains(status), "Failed at " + l +"\n" +
      "invalid value  for status: " + status)
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
