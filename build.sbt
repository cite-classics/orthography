lazy val scala212 = "2.12.10"
lazy val supportedScalaVersions = List(scala212)
ThisBuild / scalaVersion := scala212
ThisBuild / turbo := true

lazy val root = project.in(file(".")).
    aggregate(crossed.js, crossed.jvm).
    settings(
      crossScalaVersions := Nil,
      publish / skip := true
    )

lazy val crossed = crossProject(JSPlatform, JVMPlatform).in(file(".")).
    settings(
      name := "orthography",
      organization := "edu.holycross.shot.mid",
      version := "2.2.0",
      licenses += ("GPL-3.0",url("https://opensource.org/licenses/gpl-3.0.html")),

      resolvers += "Nexus" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/",
      libraryDependencies ++= Seq(
        "org.scalatest" %%% "scalatest" % "3.1.2" % "test",
        "org.wvlet.airframe" %%% "airframe-log" % "20.5.2",
        "edu.holycross.shot.cite" %%% "xcite" % "4.3.1",
        "edu.holycross.shot" %%% "ohco2" % "10.20.5",

      ),
      credentials += Credentials(Path.userHome / ".sbt" / ".credentials"),

      publishTo := Some("releases" at "https://terracotta.hpcc.uh.edu/nexus/repository/maven-releases/")

    ).jvmSettings(
      libraryDependencies ++= Seq(
        "org.scala-js" %% "scalajs-stubs" % "1.0.0" % "provided",
      )
    ).
    jsSettings(
      // JS-specific settings:
      scalaJSUseMainModuleInitializer := true,
    )

    lazy val docs = project       // new documentation project
      .in(file("docs-build")) // important: it must not be docs/
      .dependsOn(crossed.jvm)
      .enablePlugins(MdocPlugin)
      .settings(
        mdocIn := file("guide"),
        mdocOut := file("docs"),
        mdocExtraArguments := Seq("--no-link-hygiene"),
        mdocVariables := Map(
          "VERSION" -> "2.1.0"
        )
      )
