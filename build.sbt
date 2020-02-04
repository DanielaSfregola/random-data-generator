import com.typesafe.sbt.SbtGit.GitKeys._
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val standardSettings = Seq(
  organization := "com.danielasfregola",
  version := "2.9-SNAPSHOT",
  scalaVersion := "2.13.0",
  crossScalaVersions := Seq("2.13.0", "2.12.8", "2.11.8"),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")),
  homepage := Some(url("https://github.com/DanielaSfregola/random-data-generator")),
  scmInfo := Some(
    ScmInfo(url("https://github.com/DanielaSfregola/random-data-generator"),
            "scm:git:git@github.com:DanielaSfregola/random-data-generator.git")),
  apiURL := None, // TBD scaladoc needed?
  pomExtra := (
    <developers>
    <developer>
      <id>DanielaSfregola</id>
      <name>Daniela Sfregola</name>
      <url>http://danielasfregola.com/</url>
    </developer>
  </developers>
  ),
  publishMavenStyle := true,
  publishTo in ThisBuild := {
    if (version.value.trim.endsWith("SNAPSHOT")) Some(Opts.resolver.sonatypeSnapshots)
    else Some(Opts.resolver.sonatypeStaging)
  },
  gitRemoteRepo := "git@github.com:DanielaSfregola/random-data-generator.git",
  scalacOptions ++= Seq("-encoding",
                        "UTF-8",
                        "-deprecation",
                        "-Xfatal-warnings",
                        "-feature",
                        "-language:postfixOps",
                        "-unchecked"),
  scalacOptions in (Compile, doc) ++= Seq("-sourcepath", baseDirectory.value.getAbsolutePath),
  autoAPIMappings := true,
  apiURL := None,
  scalacOptions in (Compile, doc) ++= {
    val branch = if (version.value.trim.endsWith("SNAPSHOT")) "master" else version.value
    Seq(
      "-doc-source-url",
      "https://github.com/DanielaSfregola/random-data-generator/tree/" + branch + "€{FILE_PATH}.scala"
    )
  }
)

lazy val root =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Full)
    .in(file("."))
    .settings(standardSettings)
    .settings(
      name := "random-data-generator",
      libraryDependencies ++= {
        val ScalacheckShapeless = "1.2.4"
        val Spec2 = "4.5.1"

        Seq(
          "com.github.alexarchambault" %%% "scalacheck-shapeless_1.14" % ScalacheckShapeless,
          "org.scala-lang" % "scala-reflect" % scalaVersion.value % "provided",
          "org.specs2" %%% "specs2-core" % Spec2 % "test",
          "org.specs2" %%% "specs2-mock" % Spec2 % "test"
        )
      }
    )

lazy val rootJS     = root.js
lazy val rootJVM    = root.jvm
