---
title: The Latin23 orthography
layout: page
---


**Version @VERSION@**

## Example implementation

`Latin23` is an orthography using 23 alphabetic letters from the Latin alphabet.  So we'll import the library, and see what `Latin23` can do.

```scala mdoc
import edu.holycross.shot.mid.orthography._
```


It can determine if a String is valid in this orthography:

```scala mdoc
Latin23.validString("ius belli")
Latin23.validString("μῆνιν ἄειδε")
```


It can highlight bad code points in a Unicode string:

```scala mdoc:silent
println(Latin23.hiliteBadCps("latin ανδ greek"))
```
```scala mdoc:passthrough
println(">" + Latin23.hiliteBadCps("latin ανδ greek"))
```

It can tokenize a `CitableNode`.  Let's make a citable node:

```scala mdoc:silent
import edu.holycross.shot.cite._
import edu.holycross.shot.ohco2._

val nodeUrn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
val nodeText = "iam primum omnium satis constat Troia capta in ceteros saevitum esse Troianos;"
val citableNode = CitableNode(nodeUrn,nodeText)
```
And tokenize it:
```scala mdoc
val tokens = Latin23.tokenizeNode(citableNode)
```

The resulting `MidToken`s consist of a new `CitableNode` with the citation hierarchy extended one tier, plus a classifying category.  The category is a Scala `Option`:  if the token is invalid, its category will be `None`.

`Latin23` can specify all of its categories for classifying tokens:

```scala mdoc
Latin23.tokenCategories
```

And since it can tokenize a `CitableNode`, it can necessarily tokenize a `Corpus`.  


Let's make a very small corpus:

```scala mdoc:silent
val urn = CtsUrn("urn:cts:omar:stoa0179.stoa001.omar:1.1.1")
val txt = "iam primum omnium satis constat Troia capta in ceteros saevitum esse Troianos; duobus, Aeneae Antenorique, et vetusti iure hospitii et quia pacis reddendaeque Helenae semper auctores fuerunt, omne ius belli Achivos abstinuisse;"
val sourceCorpus = Corpus(Vector(CitableNode(urn,txt)))
```

And then tokenize it:
```scala mdoc
val tokenCorpus = Latin23.tokenizedCorpus(sourceCorpus)
```

The result is a new corpus, citable at the level of individual token.



MidOrthographyObject:
The MidOrthography object
- should generate vocabulary lists from lists of tokens
- should generate a vector of indexed tokens from a vector of tokens
- should generate a concordance of surface forms from lists of tokens
