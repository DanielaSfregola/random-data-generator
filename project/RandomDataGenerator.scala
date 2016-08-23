import com.typesafe.sbt.SbtGhPages._
import com.typesafe.sbt.SbtGit.{GitKeys => git}
import com.typesafe.sbt.SbtSite._
import sbt.Keys._
import sbt.{LocalProject, _}

object Resolvers {
  val resolvers = Seq(
    "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
    "Spray Repository"    at "http://repo.spray.io"
  )
}

object Dependencies {

  val TypesafeVersion = "1.3.0"
  val AkkaVersion = "2.3.6"
  val SprayVersion = "1.3.3"
  val Json4sVersion = "3.2.11"
  val Spec2Version = "2.3.13"

  val dependencies = Seq(
    "com.typesafe" % "config" % TypesafeVersion,
    "com.typesafe.akka" %% "akka-actor" % AkkaVersion,
    "io.spray" %% "spray-client" % SprayVersion,
    "io.spray" %% "spray-http" % SprayVersion,
    "io.spray" %% "spray-routing" % SprayVersion,
    "org.json4s" %% "json4s-native" % Json4sVersion,
    "org.specs2" %% "specs2-core" % Spec2Version % "test",
    "com.typesafe.akka" %% "akka-testkit" % AkkaVersion % "test"
  )
}

object RandomDataGenerator extends Build {

  val v = "0.3-SNAPSHOT"

  lazy val standardSettings = Defaults.defaultSettings ++
  Seq(
    name := "random-data-generator",
    version := v,
    scalaVersion := "2.11.7",
    organization := "com.danielasfregola",
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html")),
    homepage := Some(url("https://github.com/DanielaSfregola/random-data-generator")),
    scmInfo := Some(ScmInfo(url("https://github.com/DanielaSfregola/random-data-generator"), "scm:git:git@github.com:DanielaSfregola/random-data-generator.git")),
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
    resolvers ++= Resolvers.resolvers,

    publishMavenStyle := true,
    git.gitRemoteRepo := "git@github.com:DanielaSfregola/random-data-generator.git",

    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-Xlint",
      "-deprecation",
      "-Xfatal-warnings",
      "-feature",
      "-language:postfixOps",
      "-unchecked"
    ),
    scalacOptions in (Compile, doc) <++= baseDirectory in LocalProject("random-data-generator") map { bd =>
      Seq(
        "-sourcepath", bd.getAbsolutePath
      )
    },
    autoAPIMappings := true,
    apiURL := None,
    scalacOptions in (Compile, doc) <++= version in LocalProject("random-data-generator") map { version =>
      val branch = if(version.trim.endsWith("SNAPSHOT")) "master" else version
      Seq[String](
          "-doc-source-url", "https://github.com/DanielaSfregola/random-data-generator/tree/" + branch +"€{FILE_PATH}.scala"
        )
    }
  ) ++ site.settings 

  lazy val root = Project(id = "random-data-generator",
    base = file("."),
    settings = standardSettings ++ Seq(
      libraryDependencies ++= Dependencies.dependencies
    )
  )
}
