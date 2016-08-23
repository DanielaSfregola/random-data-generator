package com.danielasfregola.randomdatagenerator

import org.scalacheck._
import org.scalacheck.Shapeless._

trait RandomDataGenerator {

  implicit class RichArbitrary[T](arb: Arbitrary[T]) {

    def instance: T = arb.arbitrary.sample.get
  }

  def random[T](implicit arb: Arbitrary[T]): T = arb.instance
}
