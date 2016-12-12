name := "random-data-generator"

scalacOptions in Test ++= Seq("-Yrangepos")

libraryDependencies ++= {
  if (scalaVersion.value.startsWith("2.10."))
    Seq(compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full))
  else Seq()
}

libraryDependencies ++= {
  val Spec2 = "3.8.6"

  Seq(
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.4",
    "org.specs2" %% "specs2-core" % Spec2 % "test",
    "org.specs2" %% "specs2-mock" % Spec2 % "test"
  )

}

