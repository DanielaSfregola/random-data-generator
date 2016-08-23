name := "random-data-generator"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= Seq(
  "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.1",
  "org.specs2"          %% "specs2-core" % "3.7" % "test",
  "org.specs2"          %% "specs2-mock" % "3.7" % "test"
)

