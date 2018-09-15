package com.danielasfregola.randomdatagenerator.utils

import scala.util.Properties

protected[utils] object SeedVariable {

  lazy val name = "RANDOM_DATA_GENERATOR_SEED"

  lazy val value = Properties.envOrNone(name)

}
