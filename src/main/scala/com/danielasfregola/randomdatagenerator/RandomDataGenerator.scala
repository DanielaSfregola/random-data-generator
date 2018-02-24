package com.danielasfregola.randomdatagenerator

import com.danielasfregola.randomdatagenerator.utils.{SeedDetector, ShapelessLike}
import org.scalacheck._

import scala.reflect.runtime.universe._

object RandomDataGenerator extends RandomDataGenerator

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] val seed = SeedDetector.seed

  def random[T : WeakTypeTag : Arbitrary]: T = random(1).head

  def random[T : WeakTypeTag : Arbitrary](n: Int): Seq[T] = {
    val gen = Gen.listOfN(n, implicitly[Arbitrary[T]].arbitrary)
    val optSeqT = gen.apply(Gen.Parameters.default, seed)
    optSeqT.getOrElse {
      throw new Exception(s"""
          Scalacheck could not generate a random value for ${implicitly[WeakTypeTag[T]].tpe}.
          Please, make use that the Arbitrary for type A is not too restrictive
        """)
    }
  }

}
