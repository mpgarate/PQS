package edu.nyu.pqs.stopwatch.demo;

import java.util.List;
import java.util.logging.Logger;

import edu.nyu.pqs.stopwatch.api.IStopwatch;
import edu.nyu.pqs.stopwatch.impl.StopwatchFactory;

/**
 * This is a simple program that demonstrates just some of the functionality of
 * the IStopwatch interface and StopwatchFactory class in a multithreaded
 * context.
 * 
 */
public class MultithreadedThinker {

  /** use a logger instead of System.out.println */
  private static final Logger logger = Logger
      .getLogger("edu.nyu.pqs.ps4.demo.MultithreadedThinker");

  /**
   * Run the MultithreadedThinker demo application
   * 
   * @param args
   *          unused commandline arguments
   */
  public static void main(String[] args) {
    MultithreadedThinker thinker = new MultithreadedThinker();
    thinker.go();
  }

  /**
   * Starts the multithreadedthinker object. It will get a stopwatch, set a
   * number of lap times, stop the watch and then print out all the lap times
   * 
   */
  private void go() {
    Runnable runnable = new Runnable() {
      public void run() {

        IStopwatch stopwatch;
        synchronized (this) {
          List<IStopwatch> stopwatches = StopwatchFactory.getStopwatches();
          if (0 == stopwatches.size()) {
            stopwatch = StopwatchFactory.getStopwatch("55");
            stopwatch.start();
          } else {
            stopwatch = stopwatches.get(0);
          }
        }

        for (int i = 0; i < 10; i++) {
          stopwatch.getLapTimes();
          stopwatch.getLapTimes();
          stopwatch.getLapTimes();
          stopwatch.getLapTimes();
          try {
            Thread.sleep(500 * i);
          } catch (InterruptedException ie) { /* safely ignore this */
          }
          try {
            stopwatch.getLapTimes();
            stopwatch.lap();
            stopwatch.getLapTimes();
            stopwatch.getLapTimes();
          } catch (IllegalStateException ignoredException) {

          }
        }
        try {
          stopwatch.stop();
        } catch (IllegalStateException ignoredException) {
        }
        List<Long> times = stopwatch.getLapTimes();
        logger.info(stopwatch.getId() + " ::: " + times.toString());
      }
    };

    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
  }
}
