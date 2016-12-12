import com.typesafe.sbt.SbtGit.{GitKeys => git}
import com.typesafe.sbt.SbtSite._
import sbt.Keys._
import sbt.{LocalProject, _}

object RandomDataGenerator extends Build {

  lazy val standardSettings = Seq(
    name := "random-data-generator",
    version := "1.5-SNAPSHOT",
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.12.1", "2.11.8", "2.10.6"),
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
    settings = standardSettings
  )
}
