package testng_interface;

import org.testng.annotations.Test;
import org.scalatools.testing.AnnotatedFingerprint;

public class TestNGFingerprint implements AnnotatedFingerprint
{
  @Override
  public String annotationName() {
      return org.testng.annotations.Test.class.getName();
  }

  @Override
  public boolean isModule() { return false; }
}
