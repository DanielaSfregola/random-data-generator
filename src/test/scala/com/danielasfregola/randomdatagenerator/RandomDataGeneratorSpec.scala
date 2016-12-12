package com.danielasfregola.randomdatagenerator

import java.util.Currency

import org.scalacheck._
import org.specs2.mutable._

class RandomDataGeneratorSpec extends RandomDataGenerator with SpecificationLike {

  "RandomDataGenerator" should {

    "generate a random instance of a simple case class" in {
      case class Example(text: String, n: Int)

      val instance = random[Example]

      instance should beAnInstanceOf[Example]
    }

    "generate multiple instances of a simple case class" in {
      case class Example(text: String, n: Int)

      val size = 3
      val instances = random[Example](size)

      instances.distinct.size === size
      instances should beAnInstanceOf[Seq[Example]]
    }

    "generate a random instance of a non-predefined type" in {
      import scala.collection.JavaConverters._

      implicit val arbitraryCurrency: Arbitrary[Currency] = Arbitrary {
        Gen.oneOf(Currency.getAvailableCurrencies.asScala.toSeq)
      }

      val instance = random[Currency]

      instance should beAnInstanceOf[Currency]
    }

    "generate a random instance by using a custom generator" in {
      case class Person(name: String, age: Int)

      implicit val arbitraryPerson: Arbitrary[Person] = Arbitrary {
        for {
          name <- Gen.oneOf("Daniela", "John", "Martin", "Marco")
          age <- Gen.choose(0, 100)
        } yield Person(name, age)
      }

      val instance = random[Person]

      instance should beAnInstanceOf[Person]
      Seq("Daniela", "John", "Martin", "Marco").contains(instance.name) should beTrue
      (0 to 100).contains(instance.age) should beTrue
    }

    "generate a random instance by using a overridden generator of a predefined type" in {
      case class AlphaStrExample(text: String)

      implicit val arbitraryString: Arbitrary[String] = Arbitrary(Gen.alphaStr)

      val instance = random[AlphaStrExample]

      instance should beAnInstanceOf[AlphaStrExample]
      instance.text.forall(_.isLetter) should beTrue
    }

    "generate a random instance of a case class with more than 22 fields" in {
      case class BigExample(f1: String, f2: Int, f3: Long, f4: Char, f5: String,
                            f6: String, f7: Int, f8: Long, f9: Char, f10: String,
                            f11: String, f12: Int, f13: Long, f14: Char, f15: String,
                            f16: String, f17: Int, f18: Long, f19: Char, f20: String,
                            f21: String, f22: Int, f23: Long, f24: Char, f25: String,
                            f26: String, f27: Int, f28: Long, f29: Char, f30: String)

      /**
        * NOTE: You only need to generate the arbitrary manually if your
        * scala version is previous to 2.11.8.
        *
        * If you scala version is 2.11.8 or higher, the arbitrary instance
        * will be generated automatically.
        */
      implicit val arbitraryBigExample: Arbitrary[BigExample] = Arbitrary {
        for {
          f1 <- Gen.alphaStr
          f2 <- Arbitrary.arbInt.arbitrary
          f3 <- Arbitrary.arbLong.arbitrary
          f4 <- Gen.alphaNumChar
          f5 <- Gen.alphaStr
          f6 <- Gen.alphaStr
          f7 <- Arbitrary.arbInt.arbitrary
          f8 <- Arbitrary.arbLong.arbitrary
          f9 <- Gen.alphaNumChar
          f10 <- Gen.alphaStr
          f11 <- Gen.alphaStr
          f12 <- Arbitrary.arbInt.arbitrary
          f13 <- Arbitrary.arbLong.arbitrary
          f14 <- Gen.alphaNumChar
          f15 <- Gen.alphaStr
          f16 <- Gen.alphaStr
          f17 <- Arbitrary.arbInt.arbitrary
          f18 <- Arbitrary.arbLong.arbitrary
          f19 <- Gen.alphaNumChar
          f20 <- Gen.alphaStr
          f21 <- Gen.alphaStr
          f22 <- Arbitrary.arbInt.arbitrary
          f23 <- Arbitrary.arbLong.arbitrary
          f24 <- Gen.alphaNumChar
          f25 <- Gen.alphaStr
          f26 <- Gen.alphaStr
          f27 <- Arbitrary.arbInt.arbitrary
          f28 <- Arbitrary.arbLong.arbitrary
          f29 <- Gen.alphaNumChar
          f30 <- Gen.alphaStr
        } yield BigExample(f1, f2, f3, f4, f5, f6, f7, f8, f9, f10,
                           f11, f12, f13, f14, f15, f16, f17, f18, f19, f20,
                           f21, f22, f23, f24, f25, f26, f27, f28, f29, f30)
      }

      val instance = random[BigExample]

      instance should beAnInstanceOf[BigExample]
    }

  }

}
