package testng_interface;

import org.scalatools.testing.Fingerprint;
import org.scalatools.testing.Framework;
import org.scalatools.testing.Logger;
import org.scalatools.testing.Runner;


public final class TestNGFramework implements Framework
{
  private static final Fingerprint[] FINGERPRINTS =
      new Fingerprint[] { new TestNGFingerprint() };

  @Override
  public String name() { return "TestNG"; }

  @Override
  public Runner testRunner(ClassLoader testClassLoader, Logger[] loggers)
  {
    return new TestNGRunner(testClassLoader, loggers);
  }

  @Override
  public Fingerprint[] tests() {
      return FINGERPRINTS;
  }
}
