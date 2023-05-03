addSbtPlugin("com.typesafe.sbt" % "sbt-site" % "1.4.1")

addSbtPlugin("com.typesafe.sbt" % "sbt-ghpages" % "0.6.3")

addSbtPlugin("com.github.sbt" % "sbt-unidoc" % "0.5.0")

resolvers += Classpaths.sbtPluginReleases

addSbtPlugin("org.scoverage" % "sbt-coveralls" % "1.3.1")

addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.9.19")

addSbtPlugin("com.github.sbt" % "sbt-pgp" % "2.1.2")

// cross-compiling
addSbtPlugin("org.portable-scala" % "sbt-scalajs-crossproject"      % "1.1.0")
addSbtPlugin("org.scala-js"       % "sbt-scalajs"                   % "1.9.0")