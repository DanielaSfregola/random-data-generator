package com.danielasfregola.randomdatagenerator.utils

import scala.util.Try
import scala.scalajs.js

object SeedVariable {

  protected[utils] lazy val name = Try {
    val fromEnv = js.Dynamic.global.process.env.selectDynamic(SeedDetector.SeedVariableName)
    if (js.isUndefined(fromEnv)) {
      throw new Exception("Seed not defined by user")
    } else {
      fromEnv.toString
    }
  }.toOption

}
