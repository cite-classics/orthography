package edu.holycross.shot.mid.orthography


trait TypedToken[T] {
  def str: String
  def ttype: T
}


trait OrthoSystem[T] {
  def validCodePoints : Vector[String]
  def tokenize(s: String)  : Vector[TypedToken[T]]
}
