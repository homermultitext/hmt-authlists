import java.io.PrintWriter
import scala.io.Source
import edu.holycross.shot.cite._
import edu.holycross.shot.citebinaryimage._
val baseUrl  = "http://www.homermultitext.org/iipsrv?"
val pathBase = "/project/homer/pyramidal/deepzoom/"
val columns = 3
val imgWidth = 150


val db = "data/dingbats.cex"
// drop 2 header lines
val data = Source.fromFile(db).getLines.toVector.drop(2)


def formatImage(row: Vector[String]) = {
  val db = Cite2Urn(row(0))
  val label = row(1) + s", `${db}`"
  val u = Cite2Urn(row(3))

  val fullPath = pathBase + PathUtility.expandedPath(u)
  val iiif = IIIFApi(baseUrl,fullPath)
  iiif.linkedMarkdownImage(u, width=Some(imgWidth), caption=label) + s"<br/>${label}"
}

def mdRow(cells: Vector[String]): String = {
  "| " + cells.mkString(" | ") + " |"
}



val imgs = data.map(_.split("#").toVector).map(row => formatImage(row))


val tableByRows = imgs.sliding(columns,columns).toVector

val hdr = for  (c <- 1 to columns) yield " "
val separator = for  (c <- 1 to columns) yield "--- "




def intro = """
## Citable dingbats

The following dingbats are defined in the collection `urn:cite2:hmt:dingbats.v1:`

"""

val md : String = intro + mdRow(hdr.toVector) + "\n" + mdRow(separator.toVector) + "\n" + tableByRows.map(r => mdRow(r)).mkString("\n")

new java.io.PrintWriter("dingbatviewer.md"){write(md);close;}
