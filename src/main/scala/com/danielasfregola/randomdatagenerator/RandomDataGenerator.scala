package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.scalacheck.derive._
import org.scalacheck.rng.Seed

import scala.util.Properties

trait RandomDataGenerator extends ShapelessLike {

  implicit class RichArbitrary[T](arb: Arbitrary[T]) {
    def instance: T = {
      val gen = arb.arbitrary
      val optT = gen.apply(Gen.Parameters.default, RandomDataGenerator.seed)
      optT.get
    }
  }

  def random[T](implicit arb: Arbitrary[T]): T = arb.instance
}

private[randomdatagenerator] object RandomDataGenerator {

  lazy val seed: Seed = {
    println(s"[info] [RandomDataGenerator] Generating random data with seed $randomLong")
    Seed(randomLong)
  }

  private val randomLong = Properties.envOrNone("RANDOM_DATA_GENERATOR_SEED").map { seedAsString =>
      try { seedAsString.toLong }
      catch { case ex: Throwable => throw new RuntimeException("Please, provide a numeric seed", ex) }
  } getOrElse scala.util.Random.nextLong

}

trait ShapelessLike
    extends SingletonInstances
    with HListInstances
    with CoproductInstances
    with DerivedInstances
    with FieldTypeInstances
