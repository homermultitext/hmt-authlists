import java.io.PrintWriter
import scala.io.Source
import edu.holycross.shot.cite._
import edu.holycross.shot.citebinaryimage._
val baseUrl  = "http://www.homermultitext.org/iipsrv?"
val pathBase = "/project/homer/pyramidal/deepzoom/"
val columns = 3
val imgWidth = 150



// Map one row of data from CEX source to a
// linked thumbnail image
def formatImage(row: Vector[String]) = {
  val db = Cite2Urn(row(0))
  val label = row(1) + s", `${db}`"
  val u = Cite2Urn(row(3))

  val fullPath = pathBase + PathUtility.expandedPath(u)
  val iiif = IIIFApi(baseUrl,fullPath)
  iiif.linkedMarkdownImage(u, width=Some(imgWidth), caption=label) + s"<br/>${label}"
}


// Compose a row in a markdown from a Vector of Strings
// to use as individual cells
def mdRow(cells: Vector[String]): String = {
  "| " + cells.mkString(" | ") + " |"
}

def intro = """
## Citable dingbats

The following dingbats are defined in the collection `urn:cite2:hmt:dingbats.v1:`

"""


println("To generate a new dingbats viewer in markdown format:")
println("\tupdate\n\n")


// Read CEX data, generate a markdown table, and
// write to "dingbatviewer.md"
def update: Unit = {
  val db = "data/dingbats.cex"
  // drop 2 header lines
  val data = Source.fromFile(db).getLines.toVector.drop(2)
  // format markdown string for each row
  val imgs = data.map(_.split("#").toVector).map(row => formatImage(row))
  val tableByRows = imgs.sliding(columns,columns).toVector
  val hdr = for  (c <- 1 to columns) yield " "
  val separator = for  (c <- 1 to columns) yield "--- "
  val md : String = intro + mdRow(hdr.toVector) + "\n" + mdRow(separator.toVector) + "\n" + tableByRows.map(r => mdRow(r)).mkString("\n")

  new java.io.PrintWriter("dingbatviewer.md"){write(md);close;}

}
