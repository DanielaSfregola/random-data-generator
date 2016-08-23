package com.danielasfregola.randomdatagenerator

import java.util.Currency

import org.scalacheck.{Arbitrary, Gen}
import org.specs2.mutable._
import scala.collection.JavaConversions._

class RandomDataGeneratorSpec
    extends RandomDataGenerator
    with SpecificationLike {

  "RandomDataGenerator" should {

    "generate a random instance of a simple case class" in {
      import org.scalacheck.Shapeless._

      case class SimpleExample(text: String)

      val instance = random[SimpleExample]
      instance should beAnInstanceOf[SimpleExample]
      instance.text.forall(_.isLetter) === false
    }

    "generate a random instance of a non-predefined type" in {
      import org.scalacheck.Shapeless._

      implicit val arbitraryCurrency: Arbitrary[Currency] = Arbitrary {
        Gen.oneOf(Currency.getAvailableCurrencies.toSeq)
      }

      random[Currency] should beAnInstanceOf[Currency]
    }

    "generate a random instance by using a custom generator" in {
      import org.scalacheck.Shapeless._

      case class Person(name: String, age: Int)

      implicit val arbitraryPerson: Arbitrary[Person] = Arbitrary {
        for {
          name <- Gen.oneOf("Daniela", "John", "Martin", "Marco")
          age <- Gen.choose(0, 100)
        } yield Person(name, age)
      }

      random[Person] should beAnInstanceOf[Person]
    }

    "generate a random instance by using a overridden generator of a predefined type" in {
      import org.scalacheck.Shapeless._

      case class AlphaStrExample(text: String)

      implicit val arbitraryString: Arbitrary[String] = Arbitrary(Gen.alphaStr)

      val instance = random[AlphaStrExample]
      instance should beAnInstanceOf[AlphaStrExample]
      instance.text.forall(_.isLetter) === true
    }

    "generate a random instance of a case class with more than 22 fields" in {
      case class BigExample(f1: String, f2: String, f3: String, f4: String, f5: String,
                            f6: String, f7: String, f8: String, f9: String, f10: String,
                            f11: String, f12: String, f13: String, f14: String, f15: String,
                            f16: String, f17: String, f18: String, f19: String, f20: String,
                            f21: String, f22: String, f23: String, f24: String, f25: String,
                            f26: String, f27: String, f28: String, f29: String, f30: String)

      implicit val arbitraryBigExample: Arbitrary[BigExample] = Arbitrary {
        for {
          f1 <- Gen.alphaStr
          f2 <- Gen.alphaStr
          f3 <- Gen.alphaStr
          f4 <- Gen.alphaStr
          f5 <- Gen.alphaStr
          f6 <- Gen.alphaStr
          f7 <- Gen.alphaStr
          f8 <- Gen.alphaStr
          f9 <- Gen.alphaStr
          f10 <- Gen.alphaStr
          f11 <- Gen.alphaStr
          f12 <- Gen.alphaStr
          f13 <- Gen.alphaStr
          f14 <- Gen.alphaStr
          f15 <- Gen.alphaStr
          f16 <- Gen.alphaStr
          f17 <- Gen.alphaStr
          f18 <- Gen.alphaStr
          f19 <- Gen.alphaStr
          f20 <- Gen.alphaStr
          f21 <- Gen.alphaStr
          f22 <- Gen.alphaStr
          f23 <- Gen.alphaStr
          f24 <- Gen.alphaStr
          f25 <- Gen.alphaStr
          f26 <- Gen.alphaStr
          f27 <- Gen.alphaStr
          f28 <- Gen.alphaStr
          f29 <- Gen.alphaStr
          f30 <- Gen.alphaStr
        } yield BigExample(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10,
                           f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,
                           f21, f22, f23, f24, f25, f26, f27, f28, f29, f30)
      }

      random[BigExample] should beAnInstanceOf[BigExample]
    }

  }

}
