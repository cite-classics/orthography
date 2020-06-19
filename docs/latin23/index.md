---
title: The Latin23 orthography
layout: page
---


**Version 2.0.0**

## Example implementation

`Latin23` is an orthography using 23 alphabetic letters from the Latin alphabet.  So we'll import the library, and see what `Latin23` can do.

```scala
import edu.holycross.shot.mid.orthography._
```


It can determine if a String is valid in this orthography:

```scala
Latin23.validString("ius belli")
// res0: Boolean = true
Latin23.validString("μῆνιν ἄειδε")
// res1: Boolean = false
```


It can highlight bad code points in a Unicode string:

```scala
println(Latin23.hiliteBadCps("latin ανδ greek"))
```
>latin  **α**  **ν**  **δ**  greek

It can tokenize a `CitableNode`.  Let's make a citable node:

```scala
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

val nodeUrn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
val nodeText = "iam primum omnium satis constat Troia capta in ceteros saevitum esse Troianos;"
val citableNode = CitableNode(nodeUrn,nodeText)
```
And tokenize it:
```scala
val tokens = Latin23.tokenizeNode(citableNode)
// tokens: Vector[MidToken] = Vector(
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.0"),
//     "iam",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.1"),
//     "primum",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.2"),
//     "omnium",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.3"),
//     "satis",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.4"),
//     "constat",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.5"),
//     "Troia",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.6"),
//     "capta",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.7"),
//     "in",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.8"),
//     "ceteros",
//     Some(LexicalToken)
//   ),
//   MidToken(
//     CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.9"),
//     "saevitum",
// ...
```

The resulting `MidToken`s consist of a new `CitableNode` with the citation hierarchy extended one tier, plus a classifying category.  The category is a Scala `Option`:  if the token is invalid, its category will be `None`.

`Latin23` can specify all of its categories for classifying tokens:

```scala
Latin23.tokenCategories
// res4: Vector[MidTokenCategory] = Vector(
//   PraenomenToken,
//   PunctuationToken,
//   LexicalToken,
//   NumericToken
// )
```

And since it can tokenize a `CitableNode`, it can necessarily tokenize a `Corpus`.  


Let's make a very small corpus:

```scala
val urn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
val txt = "iam primum omnium satis constat Troia capta in ceteros saevitum esse Troianos; duobus, Aeneae Antenorique, et vetusti iure hospitii et quia pacis reddendaeque Helenae semper auctores fuerunt, omne ius belli Achivos abstinuisse;"
val sourceCorpus = Corpus(Vector(CitableNode(urn,txt)))
```

And then tokenize it:
```scala
val tokenCorpus = Latin23.tokenizedCorpus(sourceCorpus)
// tokenCorpus: Corpus = Corpus(
//   Vector(
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.0"),
//       "iam"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.1"),
//       "primum"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.2"),
//       "omnium"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.3"),
//       "satis"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.4"),
//       "constat"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.5"),
//       "Troia"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.6"),
//       "capta"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.7"),
//       "in"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.8"),
//       "ceteros"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.9"),
//       "saevitum"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.10"),
//       "esse"
//     ),
//     CitableNode(
//       CtsUrn("urn:cts:omar:stoa0179.stoa001.omar.lat23tkn:1.1.1.11"),
//       "Troianos"
// ...
```

The result is a new corpus, citable at the level of individual token.



MidOrthographyObject:
The MidOrthography object
- should generate vocabulary lists from lists of tokens
- should generate a vector of indexed tokens from a vector of tokens
- should generate a concordance of surface forms from lists of tokens
