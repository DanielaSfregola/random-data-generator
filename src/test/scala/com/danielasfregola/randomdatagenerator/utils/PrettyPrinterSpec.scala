package com.danielasfregola.randomdatagenerator.utils

import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.specification.Scope
import scala.collection.mutable.ListBuffer

class PrettyPrinterSpec extends SpecificationLike with Mockito {

  abstract class PrettyPrinterSpecContext extends Scope {
    val logs: ListBuffer[String] = new ListBuffer
    private def append(log: String): Unit = synchronized { logs += log; () }

    val printer = {
      val mockPrintF = (log: String) => append(log)
      spy(new PrettyPrinter(mockPrintF))
    }
  }

  "PrettyPrinter" should {

    "print an info message" in new PrettyPrinterSpecContext {
      val msg = "This is my info message"
      printer.info(msg)
      logs must haveSize(1)
      logs must_== ListBuffer(s"[info] [RandomDataGenerator] $msg")
    }

    "print an warning message" in new PrettyPrinterSpecContext {
      val msg = "This is my warning message"
      printer.warning(msg)
      logs must haveSize(1)
      logs must_== ListBuffer(s"[warn] [RandomDataGenerator] $msg")
    }

    "print an error message" in new PrettyPrinterSpecContext {
      val msg = "This is my error message"
      printer.error(msg)
      logs must haveSize(1)
      logs must_== ListBuffer(s"[error] [RandomDataGenerator] $msg")
    }

    "print an debug message" in new PrettyPrinterSpecContext {
      val msg = "This is my debug message"
      printer.debug(msg)
      logs must haveSize(1)
      logs must_== ListBuffer(s"[debug] [RandomDataGenerator] $msg")
    }
  }

}
