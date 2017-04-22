package com.danielasfregola.randomdatagenerator

import com.danielasfregola.randomdatagenerator.utils.{SeedDetector, ShapelessLike}
import org.scalacheck._

object RandomDataGenerator extends RandomDataGenerator

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] val seed = SeedDetector.seed

  def random[T](implicit arb: Arbitrary[T]): T = random(1)(arb).head

  def random[T](n: Int)(implicit arb: Arbitrary[T]): Seq[T] = {
    val gen = Gen.listOfN(n, arb.arbitrary)
    val optSeqT = gen.apply(Gen.Parameters.default, seed)
    optSeqT.get
  }

}
