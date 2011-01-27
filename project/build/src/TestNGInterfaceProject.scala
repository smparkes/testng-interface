import sbt._
import java.io.File

class TestNGInterfaceProject(info: ProjectInfo)
extends DefaultProject(info)
{
  val testng = "org.testng" % "testng" % "5.14.6"
  val testInterface = "org.scala-tools.testing" % "test-interface" % "0.5"
  override def javaCompileOptions =
    JavaCompileOption("-target") :: JavaCompileOption("1.5") :: Nil

  /*********** Publishing ***********/
  val publishTo = Resolver.file("ScalaQuery Test Repo", new File("d:/temp/repo/"))
  //val publishTo = "Scala Tools Snapshots" at "http://nexus.scala-tools.org/content/repositories/snapshots/"
  //val publishTo = "Scala Tools Releases" at "http://nexus.scala-tools.org/content/repositories/releases/"
  Credentials(Path.userHome / ".ivy2" / ".credentials", log)
  def specificSnapshotRepo =
    Resolver.url("scala-nightly").
    artifacts("http://scala-tools.org/repo-snapshots/[organization]/[module]/2.8.0-SNAPSHOT/[artifact]-[revision].[ext]").
    mavenStyle()
  val nightlyScala = ModuleConfiguration("org.scala-lang", "*", "2.8.0-.*", specificSnapshotRepo)
  override def deliverScalaDependencies = Nil
  override def disableCrossPaths = true
  override def managedStyle = ManagedStyle.Maven

  /*********** Extra meta-data for the POM ***********/
  override def pomExtra =
      (<name>TestNGInterface</name>
      <url>http://github.com/smparkes/testng-interface/</url>
      <inceptionYear>2011</inceptionYear>
      <description>An implementation of sbt's test interface for TestNG</description>
      <licenses>
        <license>
          <name>Two-clause BSD-style license</name>
          <url>http://github.com/szeiger/testng-interface/blob/master/LICENSE.txt</url>
          <distribution>repo</distribution>
        </license>
      </licenses>
      <developers>
        <developer>
          <id>smparkes</id>
          <name>Steven Parkes</name>
          <timezone>+8</timezone>
          <email>smparkes [at] smparkes.net</email>
        </developer>
      </developers>
      <scm>
        <url>http://github.com/smparkes/testng-interface/</url>
      </scm>)
}
