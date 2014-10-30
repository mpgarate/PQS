package edu.nyu.pqs.stopwatch.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

/**
 * Test some of the basic functionality of the stopwatch. These tests do not
 * attempt to measure timing, only some example usage and the number of time
 * markers returned are considered.
 * 
 * @author Michael Garate
 *
 */
public class StopwatchTest {

  @Test
  public void testStartStop() {
    Stopwatch sw =
        (Stopwatch) StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    sw.start();
    assertEquals(0, sw.getLapTimes().size());
    sw.stop();
    assertEquals(1, sw.getLapTimes().size());
  }

  @Test
  public void testLap() {
    Stopwatch sw =
        (Stopwatch) StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    sw.start();
    sw.lap();
    sw.lap();
    sw.lap();
    sw.stop();
    assertEquals(4, sw.getLapTimes().size());
  }

  @Test
  public void testReset() {
    Stopwatch sw =
        (Stopwatch) StopwatchFactory.getStopwatch(UUID.randomUUID().toString());
    sw.start();
    sw.lap();
    sw.lap();
    sw.lap();
    sw.reset();

    assertEquals(0, sw.getLapTimes().size());
  }

  @Test
  public void toStringTest() throws InterruptedException {
    Stopwatch sw =
        (Stopwatch) StopwatchFactory.getStopwatch(UUID.randomUUID().toString());

    sw.start();
    sw.lap();
    Thread.sleep(5);
    sw.lap();
    Thread.sleep(8);
    sw.lap();
    assertTrue(sw.toString().contains("started"));
    Thread.sleep(11);
    sw.lap();
    Thread.sleep(13);
    sw.lap();
    Thread.sleep(17);
    sw.stop();

    String stringVal = sw.toString();

    assertTrue(stringVal.contains("stopped"));

    for (Long duration : sw.getLapTimes()) {
      assertTrue(stringVal.contains("(" + String.valueOf(duration) + ")"));
    }
  }

}
