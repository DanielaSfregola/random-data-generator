package com.danielasfregola.randomdatagenerator

import com.danielasfregola.randomdatagenerator.utils.{SeedDetector, ShapelessLike}
import org.scalacheck._

import scala.reflect.ClassTag
import scala.util.{Success, Try}

object RandomDataGenerator extends RandomDataGenerator

trait RandomDataGenerator extends ShapelessLike {

  protected[randomdatagenerator] val seed = SeedDetector.seed

  def random[T: Arbitrary: ClassTag]: T = {
    val arbitrary = implicitly[Arbitrary[T]]
    val ct = implicitly[ClassTag[T]]
    random(1)(arbitrary, ct).head
  }

  def random[T: Arbitrary: ClassTag](n: Int): Seq[T] = {
    val gen = Gen.infiniteStream(implicitly[Arbitrary[T]].arbitrary)
    Try(gen.apply(Gen.Parameters.default, seed)) match {
      case Success(Some(v)) => v.take(n)
      case _                => explode[T]
    }
  }

  private def explode[T: ClassTag]() = {
    val classTag = implicitly[ClassTag[T]]
    val msg =
      s"""Could not generate a random value for $classTag.
         |Please, make use that the Arbitrary instance for type $classTag is not too restrictive""".stripMargin
    throw new RandomDataException(msg)
  }

}
