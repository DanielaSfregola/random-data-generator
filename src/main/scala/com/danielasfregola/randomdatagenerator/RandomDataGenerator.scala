package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.scalacheck.derive._

trait RandomDataGenerator extends ShapelessLike {

  def random[T](implicit arb: Arbitrary[T]): T = {
    val gen = arb.arbitrary
    val optT = gen.sample
    optT.get
  }
}


trait ShapelessLike
    extends SingletonInstances
    with HListInstances
    with CoproductInstances
    with DerivedInstances
    with FieldTypeInstances
