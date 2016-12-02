package com.danielasfregola.randomdatagenerator

import org.scalacheck.derive._

trait ShapelessLike
    extends SingletonInstances
    with HListInstances
    with CoproductInstances
    with DerivedInstances
    with FieldTypeInstances
