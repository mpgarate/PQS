package edu.nyu.pqs.stopwatch.demo;

import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.Stopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

public class StopwatchConcurrentEquality {

  public static void main(String[] args) {
    concurrencyEqualsTest();
  }

  public static void concurrencyEqualsTest() {
    Stopwatch sw1 = (Stopwatch) StopwatchFactory.getStopwatch("10008");

    class StopwatchTask implements Runnable {
      Stopwatch sw1;

      StopwatchTask(Stopwatch sw1) {
        this.sw1 = sw1;
      }

      public void run() {
        List<IStopwatch> stopwatches = StopwatchFactory.getStopwatches();
        Stopwatch sw2 = null;
        for (IStopwatch sw : stopwatches) {
          if ("10008".equals(sw.getId())) {
            sw2 = (Stopwatch) sw;
          }
        }

        if (!(sw1 == sw2)) {
          System.err.println("objects should have been equal");
        }
      }
    }

    for (int i = 0; i < 100000; i++) {
      new Thread(new StopwatchTask(sw1)).start();
    }
  }
}
