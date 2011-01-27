package testng_interface;

import org.testng.ITestResult;
import org.scalatools.testing.Event;
import org.scalatools.testing.Result;


abstract class AbstractEvent implements Event
{
  protected final String testName;
  protected final String msg;
  protected final Result result;
  protected final Throwable error;

  AbstractEvent(String testName, String msg, Result result, Throwable error)
  {
    this.testName = testName;
    this.msg = msg;
    this.result = result;
    this.error = error;
  }

  @Override
  public final String testName() { return testName; }

  @Override
  public final String description() { return msg; }

  @Override
  public final Result result() { return result; }

  @Override
  public final Throwable error() { return error; }

  public void logTo(RichLogger logger) { }

  static String buildName(ITestResult result)
  {
    return result.getTestClass().getName()+'.'+result.getName();
  }
}
