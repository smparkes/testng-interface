package testng_interface;

import org.testng.ITestResult;
import org.scalatools.testing.Result;


final class TestSkippedEvent extends AbstractEvent
{
  TestSkippedEvent(ITestResult result) {
      super(buildName(result), null, Result.Skipped, null);
  }

  @Override
  public void logTo(RichLogger logger)
  {
    logger.info("Test "+testName+" skipped");
  }
}
