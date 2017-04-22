package com.danielasfregola.randomdatagenerator.utils

import org.scalacheck.derive._

private[randomdatagenerator] trait ShapelessLike
    extends SingletonInstances
    with HListInstances
    with CoproductInstances
    with DerivedInstances
    with FieldTypeInstances
