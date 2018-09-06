package edu.holycross.shot.mid.orthography

object AsciiTokenTypes extends Enumeration {
  type AsciiTokenTypes = Value
  val LEXICAL, NUMERIC, PUNCTUATION = Value
}



case class AsciiToken (s: String, tokenType: AsciiTokenTypes.AsciiTokenTypes) extends   TypedToken[AsciiTokenTypes.AsciiTokenTypes] {
  def str = s
  def ttype = tokenType
}


object AsciiOrtho extends OrthoSystem[AsciiTokenTypes.AsciiTokenTypes] {
  def validCodePoints = Vector("a","b","c")
  def tokenize(s: String) = {
    val raw = for (t <- s.split("[\\. ]")) yield {
      AsciiToken(t, AsciiTokenTypes.LEXICAL)
    }
    raw.toVector
  }
}
