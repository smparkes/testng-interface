package testng_interface;

import org.testng.ITestResult;
import org.scalatools.testing.Result;


final class TestFinishedEvent extends AbstractEvent
{
  TestFinishedEvent(ITestResult result) {
      super(buildName(result), null, Result.Success, null);
  }

  @Override
  public void logTo(RichLogger logger)
  {
    logger.debug("Test "+testName+" finished");
  }
}
