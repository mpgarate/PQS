package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
 * A tool to keep track of elapsed time in a concurrent environment. This class
 * may not be extended.
 * 
 * This class is unconditionallty thread safe, but it is important to note that
 * client code is responsible for ensuring an appropriate sequence of events
 * with relation to start and stop. For example, a request to start the
 * stopwatch while it is already running will throw an IllegalStateException <
 * RuntimeException. If client threads change the start/stopped state, it may be
 * necessary to catch an IllegalStateException.
 * 
 * @author Michael Garate
 *
 */
public final class Stopwatch implements IStopwatch {

  private final static double NANOS_TO_MILLIS = 1000000f;

  private final Object lock = new Object();
  private final String id;
  private boolean isStarted = false;
  private Long startTimeNanos;
  private Long previousLapStartTimeNanos;
  private List<Long> lapTimes = Collections
      .synchronizedList(new ArrayList<Long>());

  /**
   * Get a Stopwatch instance with the given id. This method is package
   * protected so that stopwatches can only be instantiated through
   * StopwatchFactory.
   * 
   * @param id
   *          the id
   * @return the stopwatch instance
   */
  static Stopwatch getInstance(String id) {
    return new Stopwatch(id);
  }

  private Stopwatch(String id) {
    this.id = id;
  }

  /**
   * Get the id of this stopwatch.
   * 
   * @return the id
   */
  @Override
  public String getId() {
    return id;
  }

  /**
   * Set the state of this stopwatch to started and store the current time. Note
   * that this does not create any lap markers.
   * 
   * @throws IllegalStateException
   *           if the stopwatch is already started
   */
  @Override
  public void start() {
    Long startTimeNanos = System.nanoTime();

    synchronized (lock) {
      if (true == isStarted) {
        throw new IllegalStateException();
      }

      startTimeNanos = startTimeNanos;
      previousLapStartTimeNanos = startTimeNanos;
      isStarted = true;
    }
  }

  /**
   * Store a lap duration.
   * 
   * @see getLapTimes()
   * @throws IllegalStateException
   *           if called when the stopwatch isn't running
   */
  @Override
  public void lap() {
    synchronized (lock) {
      Long lapTimeNanos = System.nanoTime();

      if (!isStarted) {
        throw new IllegalStateException();
      }

      lapTimes.add(lapTimeNanos - previousLapStartTimeNanos);

      previousLapStartTimeNanos = lapTimeNanos;
    }
  }

  /**
   * Set the state to stopped and add the current time as a final lap indicator.
   * 
   * @throws IllegalStateException
   *           if called when the stopwatch isn't running
   */
  @Override
  public void stop() {
    synchronized (lock) {
      lap();
      isStarted = false;
    }
  }

  /**
   * Set the state to stopped, clear the start time, and clear all laps.
   */
  @Override
  public void reset() {
    synchronized (lock) {
      isStarted = false;
      startTimeNanos = null;
      lapTimes = Collections.synchronizedList(new ArrayList<Long>());
    }
  }

  /**
   * Get a list of lap times from this stopwatch. This method contains a
   * synchronized block iterating once over the recorded lap times, and calling
   * this method frequently while another thread attempts to record a lap will
   * reduce timing accuracy, especially with a large number of recorded lap
   * times.
   * 
   * @return list of lap times
   * @see java.util.Collections.synchronizedList
   */
  @Override
  public List<Long> getLapTimes() {
    synchronized (lock) {

      // Collections.synchronizedList requires synchronization during iteration
      List<Long> lapTimesToReturn;
      synchronized (lapTimes) {
        lapTimesToReturn = new ArrayList<Long>(lapTimes);
      }

      for (Long lapTimeNanos : lapTimesToReturn) {
        int lapIndex = lapTimesToReturn.indexOf(lapTimeNanos);
        Long lapTimeMillis = Math.round(lapTimeNanos / NANOS_TO_MILLIS);
        lapTimesToReturn.set(lapIndex, lapTimeMillis);
      }

      return lapTimesToReturn;
    }
  }

  /**
   * Returns the hashCode value for this stopwatch.
   */
  @Override
  public int hashCode() {
    int result = 17;

    result = 31 * result + (isStarted ? 0 : 1);
    result = 31 * result + lapTimes.hashCode();

    return result;
  }

  /**
   * Two stopwatches are considered equal if they are the same instance or if
   * they have matching lap times and started/stopped state. Equal stopwatches
   * must have matching lists from getLapTimes() according to List.equals(). The
   * state of isStarted is also considered for equality because it implies an
   * end time that could cause the lists to differ. The absolute start time of a
   * stopwatch is not considered because it does not necessarily affect the lap
   * times. This allows a client to run the same procedure with a stopwatch at
   * different times and perform a strict comparison of the results using
   * equals().
   * 
   * Since the IStopwatch interface does not refine the equals contract to
   * permit comparisons across classes that implement the interface, this equals
   * method will not consider all IStopwatch objects for equality, only
   * Stopwatch objects.
   * 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (null == obj) {
      return false;
    }

    // do not use instanceof because classes extending Stopwatch are disallowed
    if (obj.getClass() != getClass()) {
      return false;
    }

    Stopwatch otherStopwatch = (Stopwatch) obj;

    if (otherStopwatch.isStarted != this.isStarted) {
      return false;
    }

    if (!getLapTimes().equals(otherStopwatch.getLapTimes())) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append(isStarted ? "started" : "stopped");
    sb.append(" : [");
    Iterator<Long> itr = getLapTimes().iterator();
    while (itr.hasNext()) {
      Long duration = itr.next();
      sb.append("(" + String.valueOf(duration) + ")");
      if (itr.hasNext()) {
        sb.append(", ");
      }

    }
    sb.append("]");

    return sb.toString();
  }
}
