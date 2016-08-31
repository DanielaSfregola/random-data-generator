package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.scalacheck.derive._

trait RandomDataGenerator extends ShapelessLike {

  implicit class RichArbitrary[T](arb: Arbitrary[T]) {

    def instance: T = arb.arbitrary.sample.get
  }

  def random[T](implicit arb: Arbitrary[T]): T = arb.instance
}

trait ShapelessLike
    extends SingletonInstances
    with HListInstances
    with CoproductInstances
    with DerivedInstances
    with FieldTypeInstances
