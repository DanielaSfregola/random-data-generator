package com.danielasfregola.randomdatagenerator

import com.danielasfregola.randomdatagenerator.utils.{SeedDetector, ShapelessLike}
import org.scalacheck._

import scala.reflect.runtime.universe._

object RandomDataGenerator extends RandomDataGenerator

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] val seed = SeedDetector.seed

  def random[T: WeakTypeTag: Arbitrary]: T = random(1).head

  def random[T: WeakTypeTag: Arbitrary](n: Int): Seq[T] = {
    val gen = Gen.listOfN(n, implicitly[Arbitrary[T]].arbitrary)
    val optSeqT = gen.apply(Gen.Parameters.default, seed)
    optSeqT.getOrElse(explode)
  }

  private def explode[T: WeakTypeTag]() = {
    val tpe = implicitly[WeakTypeTag[T]].tpe
    val msg =
      s"""Could not generate a random value for $tpe.
         |Please, make use that the Arbitrary instance for type $tpe is not too restrictive""".stripMargin
    throw new RandomDataException(msg)
  }

}
