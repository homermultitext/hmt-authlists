import edu.holycross.shot.cite._
import scala.io.Source

val collectionsToFiles = Map(
 "place"   -> "hmtplaces.cex",
 "pers" ->   "hmtnames.cex"  //,
  //"astronomy.cex" -> "astro",
  //"citedworks.cex" -> "work"
)



def validate(collectionName: String) = {
  if (collectionsToFiles.keySet.contains(collectionName)) {
    val lines = Source.fromFile("data/" + collectionsToFiles(collectionName)).getLines.toVector.filter(_.nonEmpty)
    val urns: Vector[String] = lines.drop(2).map(l => {
      val cols = l.split("#")
      try {
        val urn = Cite2Urn(cols(0))
        urn.objectComponent.toString
      } catch {
        case t: Throwable => {
          println("Failed at " + cols(0) + " from " + l)
          ""
        }
      }
    })

    val numsOnly = urns.map(_.replaceFirst(collectionName,"").toInt).sorted.reverse

    println(s"${collectionsToFiles(collectionName)}: ${urns.size} entries with valid URNs.\nHighest value = ${collectionName}${numsOnly(0)}")

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
println("\t\"place\"")
println("\t\"pers\"")
