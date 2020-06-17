package edu.holycross.shot.mid.orthography

import org.scalatest.FlatSpec


class UnicodeUtilsSpec extends FlatSpec {

  "The UnicodeUtils object" should "convert a String to a Vector of code points" in {
    val s = "μῆνιν ἄειδε, θεά."
    val cps = UnicodeUtils.stringToCps(s)
    val expectedSize = 17
    assert(cps.size == expectedSize)
  }

  it should "convert a Vector of code points to a String" in {
    val wrath = Vector(956, 8134, 957, 953, 957)
    val expected = "μῆνιν"
    assert (UnicodeUtils.cpsToString(wrath) == expected)
  }

}
