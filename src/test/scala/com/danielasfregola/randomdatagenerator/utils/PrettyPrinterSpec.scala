package com.danielasfregola.randomdatagenerator.utils

import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.specification.Scope

class PrettyPrinterSpec extends SpecificationLike with Mockito {

  abstract class PrettyPrinterSpecContext extends Scope {
    val printer = {
      val mockPrintF = {_: String => (): Unit}
      spy(new PrettyPrinter(mockPrintF))
    }
  }

  "PrettyPrinter" should {

    "print an info message" in new PrettyPrinterSpecContext {
      val msg = "This is my info message"
      printer.info(msg)
      there was one(printer).print(s"[info] [RandomDataGenerator] $msg")
    }

    "print an warning message" in new PrettyPrinterSpecContext {
      val msg = "This is my warning message"
      printer.warning(msg)
      there was one(printer).print(s"[warn] [RandomDataGenerator] $msg")
    }

    "print an error message" in new PrettyPrinterSpecContext {
      val msg = "This is my error message"
      printer.error(msg)
      there was one(printer).print(s"[error] [RandomDataGenerator] $msg")
    }

    "print an debug message" in new PrettyPrinterSpecContext {
      val msg = "This is my debug message"
      printer.debug(msg)
      there was one(printer).print(s"[debug] [RandomDataGenerator] $msg")
    }
  }

}
