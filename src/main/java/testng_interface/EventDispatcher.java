package testng_interface;

import java.io.IOException;
import java.util.HashSet;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.scalatools.testing.EventHandler;


final class EventDispatcher implements ITestListener
{
  private final RichLogger logger;
  private final HashSet<String> reported = new HashSet<String>();
  private final EventHandler handler;
  private final boolean quiet, verbose;
  private OutputCapture capture;

  EventDispatcher(RichLogger logger,
                  EventHandler handler,
                  boolean quiet,
                  boolean verbose)
  {
    this.logger = logger;
    this.handler = handler;
    this.quiet = quiet;
    this.verbose = verbose;
  }

/*
  @Override
  public void testAssumptionFailure(Failure failure)
  {
    uncapture(true);
    postIfFirst(new TestAssumptionFailedEvent(failure));
  }
*/

  @Override
  public void onTestFailure(ITestResult result)
  {
    uncapture(true);
    postIfFirst(new TestFailedEvent(result));
  }

  @Override
  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

  @Override
  public void onTestSuccess(ITestResult result)
  {
    uncapture(false);
    postIfFirst(new TestFinishedEvent(result));
  }

  @Override
  public void onTestSkipped(ITestResult result) {
      postIfFirst(new TestSkippedEvent(result));
  }

  @Override
  public void onTestStart(ITestResult result)
  {
    debugOrInfo("Test "+AbstractEvent.buildName(result)+" started");
    capture();
  }

  @Override
  public void onFinish(ITestContext context)
  {
      double runTime =
          (context.getEndDate().getTime() - context.getStartDate().getTime())/
          1000.0;
      debugOrInfo("Test run finished: "+
                  context.getFailedTests().size()+" failed, "+
                  context.getSkippedTests().size()+" skipped, "+
      context.getAllTestMethods().length+" total, "+(runTime)+"s");
  }

  @Override
  public void onStart(ITestContext context)
  {
    debugOrInfo("Test run started");
  }

  private void postIfFirst(AbstractEvent e)
  {
    e.logTo(logger);
    if(reported.add(e.testName())) handler.handle(e);
  }

  void post(AbstractEvent e)
  {
    e.logTo(logger);
    handler.handle(e);
  }

  private void capture()
  {
    if(quiet && capture == null)
      capture = OutputCapture.start();
  }

  void uncapture(boolean replay)
  {
    if(quiet && capture != null)
    {
      capture.stop();
      if(replay)
      {
        try { capture.replay(); }
        catch(IOException ex) { logger.error("Error replaying captured stdio", ex); }
      }
      capture = null;
    }
  }

  private void debugOrInfo(String msg)
  {
    if(verbose) logger.info(msg);
    else logger.debug(msg);
  }
}
