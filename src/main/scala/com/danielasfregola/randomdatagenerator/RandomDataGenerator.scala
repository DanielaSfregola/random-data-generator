package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.scalacheck.rng.Seed

import scala.util.Properties

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] var seed = RandomDataGenerator.seed

  def random[T](implicit arb: Arbitrary[T]): T = random(1)(arb).head

  def random[T](n: Int)(implicit arb: Arbitrary[T]): Seq[T] = {
    val gen = Gen.listOfN(n, arb.arbitrary)
    val optSeqT = gen.apply(Gen.Parameters.default, seed)
    seed = seed.next
    optSeqT.get
  }

}

private[randomdatagenerator] object RandomDataGenerator {

  val SeedVariableName = "RANDOM_DATA_GENERATOR_SEED"

  lazy val seed: Seed = {
    prettyPrint("info", s"Generating random data with seed $selectedSeed")
    Seed(selectedSeed)
  }

  private val selectedSeed = Properties.envOrNone(SeedVariableName).map {
    seedAsString =>
      prettyPrint("info", s"Variable $SeedVariableName detected: setting seed to $seedAsString")
      try {
        seedAsString.toLong
      } catch {
        case ex: Throwable => throw new RuntimeException("Please, provide a numeric seed", ex)
      }
  } getOrElse {
    prettyPrint("info", s"No variable $SeedVariableName detected: setting seed to random number")
    scala.util.Random.nextLong
  }

  private def prettyPrint(level: String, msg: String) =
    println(s"[$level] [RandomDataGenerator] $msg")

}
