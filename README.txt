An implementation of sbt's test interface <http://github.com/harrah/test-interface>
for TestNG. This allows you to TestNG <http://www.testng.org/> tests from sbt.

This code is a fairly simple/rough edit of
junit-interface <http://github.com/szeiger/junit-interface>.

Some prelimiary use instructions (via jukkapi):

1. build testng-interface sources with sbt and copy the resulting jar
   to lib directory of your project

2. add the following to your project configuration:

 def extraFramework = new TestFramework("testng_interface.TestNGFramework")
 override def testFrameworks: Seq[TestFramework] =
    super.testFrameworks ++ Seq(extraFramework)

3. set build.scala.version to 2.8.x, didn't work using 2.7.x
