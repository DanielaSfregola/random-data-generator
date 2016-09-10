random-data-generator
=====================

[![Build Status](https://travis-ci.org/DanielaSfregola/random-data-generator.svg?branch=master)](https://travis-ci.org/DanielaSfregola/random-data-generator) [![Coverage Status](https://img.shields.io/coveralls/DanielaSfregola/random-data-generator.svg)](https://coveralls.io/r/DanielaSfregola/random-data-generator?branch=master) [![License](http://img.shields.io/:license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0.txt)

A library to generate random data for test purposes, using [ScalaCheck](https://github.com/rickynils/scalacheck) and [scalacheck-shapeless](https://github.com/alexarchambault/scalacheck-shapeless).

Setup
-----
If you don't have it already, make sure you add the Maven Central as resolver in your SBT settings:

```scala
resolver += Resolver.sonatypeRepo("releases")
```

Also, you need to include the library as your dependency:
```scala
libraryDependencies += "com.danielasfregola" %% "random-data-generator" % "1.2"
```

Usage
-----
Make sure to extends [`RandomDataGenerator`](https://github.com/DanielaSfregola/random-data-generator/blob/master/project/RandomDataGenerator.scala) trait in your tests.

Assuming you have a case class defined as following:
```scala
case class Example(text: String, n: Int)
```

then you can just create a random instance of your case class as following:
```scala
val example: Example = random[Example]
// Example(ਈ䈦㈾钜㔪旅ꪔ墛炝푰⡨䌆ᵅ퍧咪, 73967257)
```

Have a look at the [tests](https://github.com/DanielaSfregola/random-data-generator/blob/master/src/test/scala/com/danielasfregola/randomdatagenerator/RandomDataGeneratorSpec.scala) for working examples on how to use the library and on how to generate manual instances of `Arbitrary[T]` when needed.

How to Fix your Seed
-------------------------------
When investigating bugs or test failures, it can be useful to reproduce the same generated data of a specific session.

For every session, a seed is selected and communicated in the logs. The log message will look similar to the following:
```bash
[info] [RandomDataGenerator] Generating random data with seed -2481216758852790303
```

To generate the same data again, all you need to do is specify an environment variable indicating the seed number to use:
```bash
export RANDOM_DATA_GENERATOR_SEED=-2481216758852790303
```

Once you are done, remember to remove the environment variable:
```bash
unset RANDOM_DATA_GENERATOR_SEED
```

Snapshot Versions
-----------------
To use a snapshot version of this library, make sure you have the resolver for maven central (snapshot repositories) in your SBT settings:
```scala
resolver += Resolver.sonatypeRepo("snapshots")
```

Then, add the library as your dependency:
```scala
libraryDependencies += "com.danielasfregola" %% "random-data-generator" % "1.3-SNAPSHOT"
```
