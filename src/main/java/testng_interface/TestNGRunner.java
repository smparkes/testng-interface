package testng_interface;

import org.testng.TestNG;
import org.scalatools.testing.EventHandler;
import org.scalatools.testing.Fingerprint;
import org.scalatools.testing.Logger;
import org.scalatools.testing.Runner2;


final class TestNGRunner extends Runner2 {
  private final ClassLoader testClassLoader;
  private final RichLogger logger;

  TestNGRunner(ClassLoader testClassLoader, Logger[] loggers)
  {
    this.testClassLoader = testClassLoader;
    this.logger = new RichLogger(loggers);
  }

  @Override
  public void run(String testClassName,
                  Fingerprint fingerprint,
                  EventHandler eventHandler, String [] args)
  {
    boolean quiet = false, verbose = false;
    for(String s : args)
    {
      if("-q".equals(s)) quiet = true;
      else if("-v".equals(s)) verbose = true;
    }
    for(String s : args)
    {
      if("+q".equals(s)) quiet = false;
      else if("+v".equals(s)) verbose = false;
    }
    EventDispatcher ed =
        new EventDispatcher(logger, eventHandler, quiet, verbose);
    org.testng.TestNG testng = new org.testng.TestNG(false);
    testng.setVerbose(verbose ? 2 : 0);
    testng.addListener(ed);
    try
    {
      Class<?> cl = testClassLoader.loadClass(testClassName);
      Class[] classes = new Class[] {cl};
      testng.setTestClasses(classes);
      try { testng.run(); } finally { ed.uncapture(true); }
    } catch(Exception ex) {
        ed.post(new TestExecutionFailedEvent(testClassName, ex));
    }
  }
}
