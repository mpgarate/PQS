package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for IStopwatch objects.
 * It maintains references to all created IStopwatch objects and provides a
 * convenient method for getting a list of those objects.
 * 
 */
public class StopwatchFactory {

  private final static Map<String, IStopwatch> stopwatches =
      new ConcurrentHashMap<String, IStopwatch>();

  /**
   * Creates and returns a new thread-safe IStopwatch object
   * 
   * @param id
   *          The identifier of the new object
   * @return The new IStopwatch object
   * @throws IllegalArgumentException
   *           if <code>id</code> is empty, null, or already taken.
   */
  public static IStopwatch getStopwatch(String id) {
    if (null == id || id.equals("")) {
      throw new IllegalArgumentException("id must not be empty");
    }

    IStopwatch stopwatch;

    synchronized (stopwatches) {
      if (null != stopwatches.get(id)) {
        throw new IllegalArgumentException("id is already taken");
      }

      stopwatch = Stopwatch.getInstance(id);
      stopwatches.put(id, stopwatch);
    }

    return stopwatch;
  }

  /**
   * Returns a list of all created stopwatches
   * 
   * @return a List of all created IStopwatch objects. Returns an empty list if
   *         no IStopwatches have been created.
   * @see java.util.concurrent.ConcurrentHashMap
   */
  public static List<IStopwatch> getStopwatches() {
    // ConcurrentHashMap iterators are designed for use by one thread at a time.
    synchronized (stopwatches) {
      return new ArrayList<IStopwatch>(stopwatches.values());
    }
  }
}
