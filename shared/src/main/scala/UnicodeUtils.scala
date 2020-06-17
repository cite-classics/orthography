package edu.holycross.shot.mid.orthography


import scala.scalajs.js.annotation._
import scala.annotation.tailrec

@JSExportTopLevel("UnicodeUtils")
object UnicodeUtils {

  /** Recursively create a Vector of code points for a String.
  *
  * @param s String to convert to code points.
  * @param cpVector Code points seen so far.
  * @param idx Index into s of code points.
  */
  @tailrec def stringToCps(
    s: String,
    cpVector: Vector[Int] = Vector.empty[Int],
    idx : Int = 0) : Vector[Int] = {

  	if (idx >= s.length) {
  		cpVector

  	} else {
  		val cp = s.codePointAt(idx)
  		stringToCps(s, cpVector :+ cp, idx + java.lang.Character.charCount(cp))
  	}
  }

def cpsToString(v: Vector[Int]) = {
  val chs = v.map { cp =>
    new String(Character.toChars(cp))
  }
  chs.mkString
}
}
