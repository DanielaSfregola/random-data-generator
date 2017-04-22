package com.danielasfregola.randomdatagenerator.utils

import org.scalacheck.rng.Seed
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.specification.Scope

class SeedDetectorSpec extends SpecificationLike with Mockito {

  abstract class SeedDetectorSpecContext extends Scope {
    val myRandomLong = scala.util.Random.nextLong

    def buildSeedDetector(myEnvVariable: Option[String]) = new SeedDetector {
      override protected lazy val logger = mock[PrettyPrinter]

      override protected lazy val envVariable = myEnvVariable
      override protected def randomLong = myRandomLong
    }
  }

  "SeedDetector" should {

    "when RANDOM_DATA_GENERATOR_SEED is not defined" should {
      "randomly select a seed value" in new SeedDetectorSpecContext {
        val seedDetector = buildSeedDetector(myEnvVariable = None)
        seedDetector.seed === Seed(myRandomLong)
      }
    }

    "when RANDOM_DATA_GENERATOR_SEED is defined" should {
      "set seed to the variable value" in new SeedDetectorSpecContext {
        val mySeed = "1234567"
        val seedDetector = buildSeedDetector(myEnvVariable = Some(mySeed))
        seedDetector.seed === Seed(mySeed.toLong)
      }

      "throw exception if the variable value is not numeric" in new SeedDetectorSpecContext {
        val mySeed = "not-a-valid-value"
        val seedDetector = buildSeedDetector(myEnvVariable = Some(mySeed))
        seedDetector.seed should throwA[RuntimeException]
      }
    }
  }

}
