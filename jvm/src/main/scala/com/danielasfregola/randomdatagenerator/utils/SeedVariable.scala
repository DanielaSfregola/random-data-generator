package com.danielasfregola.randomdatagenerator.utils

import scala.util.Properties

object SeedVariable {

  protected[utils] lazy val name = Properties.envOrNone(SeedDetector.SeedVariableName)

}
