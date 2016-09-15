name := "random-data-generator"

version := "1.4-SNAPSHOT"

scalaVersion := "2.11.7"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  val Spec2 = "3.8.4"

  Seq(
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.1",
    "org.specs2" %% "specs2-core" % Spec2 % "test",
    "org.specs2" %% "specs2-mock" % Spec2 % "test"
  )
}

