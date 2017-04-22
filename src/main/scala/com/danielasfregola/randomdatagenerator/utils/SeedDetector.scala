package com.danielasfregola.randomdatagenerator.utils

import org.scalacheck.rng.Seed

import scala.util.{Failure, Properties, Success, Try}

private[randomdatagenerator] object SeedDetector extends SeedDetector

private[randomdatagenerator] trait SeedDetector {

  protected lazy val logger = new PrettyPrinter()
  private lazy val SeedVariableName = "RANDOM_DATA_GENERATOR_SEED"

  lazy val seed: Seed = createSeedObj(seedValue)

  private def createSeedObj(seedValue: Long): Seed = {
    logger.info(s"Generating random data using seed $seedValue")
    logger.info(s"Replicate this session by setting $SeedVariableName=$seedValue")
    Seed(seedValue)
  }

  private lazy val seedValue: Long = optLongVariable match {
    case Some(preSelectedSeed) =>
      logger.info(s"Variable $SeedVariableName detected: setting seed to $preSelectedSeed")
      preSelectedSeed
    case None =>
      logger.info(s"No variable $SeedVariableName detected: setting seed to random number")
      randomLong
  }

  private lazy val optLongVariable: Option[Long] = envVariable.map { value =>
    Try(value.toLong) match {
      case Success(l) => l
      case Failure(ex) => throw new RuntimeException(s"Please, provide a numeric value for $SeedVariableName", ex)
    }
  }

  protected lazy val envVariable: Option[String] = Properties.envOrNone(SeedVariableName)
  protected def randomLong = scala.util.Random.nextLong
}
