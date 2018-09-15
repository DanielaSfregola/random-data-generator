package com.danielasfregola.randomdatagenerator

import java.util.Currency

import org.scalacheck._
import org.specs2.mutable._

class JVMRandomDataGeneratorSpec extends RandomDataGenerator with SpecificationLike {

  "RandomDataGenerator" should {

    "generate a random instance of a non-predefined type" in {
      import scala.collection.JavaConverters._

      implicit val arbitraryCurrency: Arbitrary[Currency] = Arbitrary {
        Gen.oneOf(Currency.getAvailableCurrencies.asScala.toSeq)
      }

      val instance = random[Currency]

      instance should beAnInstanceOf[Currency]
    }

    "throw an exception when the arbitrary is too restrictive" in {
      case class Example(text: String, n: Int)

      implicit val restrictive = Arbitrary(Gen.chooseNum(1, 100).suchThat(_ > 200))
      val expectedException = new RandomDataException(
        """Could not generate a random value for Example.
          |Please, make use that the Arbitrary instance for type Example is not too restrictive""".stripMargin)
      random[Example] must throwA(expectedException)
    }

  }

}
