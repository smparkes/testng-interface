package testng_interface;

import org.testng.ITestResult;
import org.scalatools.testing.Result;


final class TestFailedEvent extends AbstractEvent
{
  private final ITestResult result;

  TestFailedEvent(ITestResult result)
  {
      super(buildName(result),
            result.getThrowable().getMessage(),
            Result.Failure,
            result.getThrowable());
    this.result = result;
  }

  @Override
  public void logTo(RichLogger logger)
  {
    logger.error("Test "+AbstractEvent.buildName(result)+" failed: "+
                 result.getThrowable().getMessage(),
                 error);
  }
}
