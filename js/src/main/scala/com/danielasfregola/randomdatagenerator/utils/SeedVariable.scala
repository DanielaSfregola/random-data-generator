package com.danielasfregola.randomdatagenerator.utils

import scala.util.Try
import scala.scalajs.js

protected[utils] object SeedVariable {

  lazy val name = "RANDOM_DATA_GENERATOR_SEED"

  lazy val value = Try {
    val fromEnv = js.Dynamic.global.process.env.selectDynamic(name)
    if (js.isUndefined(fromEnv)) {
      throw new Exception("Seed not defined by user")
    } else {
      fromEnv.toString
    }
  }.toOption

}
