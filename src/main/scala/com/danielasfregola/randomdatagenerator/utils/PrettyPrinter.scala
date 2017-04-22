package com.danielasfregola.randomdatagenerator.utils

private[randomdatagenerator] class PrettyPrinter(printF: String => Unit = println) {

  def info(msg: String) = prettyPrint("info", msg)
  def warning(msg: String) = prettyPrint("warn", msg)
  def error(msg: String) = prettyPrint("error", msg)
  def debug(msg: String) = prettyPrint("debug", msg)

  private def prettyPrint(level: String, msg: String) =
    printF(s"[$level] [RandomDataGenerator] $msg")
}
