import edu.holycross.shot.cite._
import scala.io.Source

val filesAndNames = Map(
  "hmtplaces.csv" -> "place",
  "hmtnames.csv" -> "pers",
  "astronomy.csv" -> "astro",
  "citedworks.csv" -> "work"
)


for (f <- filesAndNames.keySet) {
  val lines = Source.fromFile("data/" + f).getLines.toVector.filter(_.nonEmpty)

  val urns: Vector[String] = lines.drop(1).map(l => {
    val cols = l.split(",")
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

  val subOut = filesAndNames(f)
  val numsOnly = urns.map(_.replaceFirst(subOut,"").toInt).sorted.reverse
  println(s"${f}: ${urns.size} entries.  Highest value = ${subOut}${numsOnly(0)}")
}

//astronomy
