package com.danielasfregola.randomdatagenerator

import com.danielasfregola.randomdatagenerator.utils.{SeedDetector, ShapelessLike}
import org.scalacheck._
import org.scalacheck.rng.Seed

import scala.annotation.tailrec

object RandomDataGenerator extends RandomDataGenerator

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] val seed: Seed = SeedDetector.seed

  def random[T](implicit arb: Arbitrary[T]): T = random(1)(arb).head

  @tailrec
  final def random[T](n: Int)(implicit arb: Arbitrary[T]): Seq[T] = {
    val gen = Gen.listOfN(n, arb.arbitrary)
    val optSeqT = gen.apply(Gen.Parameters.default, seed)
    optSeqT match {
      case Some(seqT) => seqT
      case None => random(n)(arb)
    }
  }

}
