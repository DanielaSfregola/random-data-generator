package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.specs2.mutable._

class JSRandomDataGeneratorSpec extends RandomDataGenerator with SpecificationLike {

  "RandomDataGenerator" should {

    "throw an exception when the arbitrary is too restrictive" in {
      case class Example(text: String, n: Int)

      implicit val restrictive = Arbitrary(Gen.chooseNum(1, 100).suchThat(_ > 200))
      random[Example] must throwA[RandomDataException]
    }

  }

}
