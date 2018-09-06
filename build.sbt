
name := "MID orthography"

crossScalaVersions in ThisBuild := Seq("2.10.6", "2.11.8", "2.12.4")
scalaVersion := (crossScalaVersions in ThisBuild).value.last


lazy val root = project.in(file(".")).
    aggregate(crossedJVM, crossedJS).
    settings(
      publish := {},
      publishLocal := {}

    )
lazy val crossed = crossProject.in(file(".")).
    settings(
      name := "orthography",
organization := "edu.holycross.shot.mid",
version := "0.0.1",
licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),

resolvers += Resolver.jcenterRepo,
resolvers += Resolver.bintrayRepo("neelsmith", "maven"),


libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.github.pathikrit" %% "better-files" % "3.5.0"
)
).   jvmSettings(
      tutTargetDirectory := file("docs"),
      tutSourceDirectory := file("shared/src/main/tut")

    ).
    jsSettings(
      skip in packageJSDependencies := false,
      scalaJSUseMainModuleInitializer in Compile := true
    )



lazy val crossedJVM = crossed.jvm.enablePlugins(TutPlugin)
lazy val crossedJS = crossed.js.enablePlugins(ScalaJSPlugin)
