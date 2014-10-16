package edu.nyu.pqs.stopwatch.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.Test;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.Stopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

public class StopwatchEqualityTest {
  @Test
  public void reflexiveTest() {
    IStopwatch sw = StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    assertTrue(sw.equals(sw));
    assertTrue(sw.hashCode() == sw.hashCode());
  }

  @Test
  public void emptyWatchesEqualTest() {
    IStopwatch sw1 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    IStopwatch sw2 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    assertTrue(sw1.equals(sw2));
    assertTrue(sw1.hashCode() == sw2.hashCode());
  }

  @Test
  public void stopwatchesNotEqualTimesTest() throws InterruptedException {
    IStopwatch sw1 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    IStopwatch sw2 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());

    sw2.start();
    Thread.sleep(100);
    sw2.stop();

    assertFalse(sw1.equals(sw2));
    assertFalse(sw1.hashCode() == sw2.hashCode());
  }

  @Test
  public void stopwatchNotEqualStartStateTest() {
    IStopwatch sw1 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    IStopwatch sw2 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());

    sw2.start();

    assertFalse(sw1.equals(sw2));
    assertFalse(sw1.hashCode() == sw2.hashCode());
  }

  @Test
  public void nullIsFalseTest() {
    IStopwatch sw = StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    assertFalse(sw.equals(null));
  }

  @Test
  public void twoObjectsTest() {
    IStopwatch sw1 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    IStopwatch sw2 = sw1;
    assertTrue(sw1.equals(sw2));
    assertTrue(sw1.hashCode() == sw2.hashCode());
  }

  @Test
  public void threeObjectsTest() {
    IStopwatch sw1 =
        StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    IStopwatch sw2 = sw1;
    Stopwatch sw3 = (Stopwatch) sw2;

    assertTrue(sw1.equals(sw2));
    assertTrue(sw1.hashCode() == sw2.hashCode());

    assertTrue(sw2.equals(sw3));
    assertTrue(sw2.hashCode() == sw3.hashCode());

    assertTrue(sw3.equals(sw1));
    assertTrue(sw3.hashCode() == sw1.hashCode());
  }

  @Test
  public void stopwatchFactoryTest() {
    String sw1Id = UUID.randomUUID().toString();
    IStopwatch sw1 = StopwatchFactory.getStopwatch(sw1Id);

    List<IStopwatch> stopwatches = StopwatchFactory.getStopwatches();

    IStopwatch sw2 = null;

    for (IStopwatch sw : stopwatches) {
      if (sw1Id.equals(sw.getId())) {
        sw2 = sw;
      }
    }

    assertTrue(sw1.equals(sw2));
    assertTrue(sw1.hashCode() == sw2.hashCode());
  }

}
