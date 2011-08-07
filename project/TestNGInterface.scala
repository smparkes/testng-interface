import sbt._
import Keys._

object TestNGInterface extends Build {
  lazy val root = Project("testng-interface", file(".")) settings (
    organization := "testng_interface",
    version := "0.2-SNAPSHOT",
    scalaVersion := "2.9.0-1",
    libraryDependencies ++= Seq(
      "org.testng" % "testng" % "5.14.6",
      "org.scala-tools.testing" % "test-interface" % "0.5"
    )
  )
}
