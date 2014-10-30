package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

/**
* @author Neha
* A thread-safe object that can be used for timing laps.  The stopwatch
* objects are created in the StopwatchFactory.  Different threads can
* share a single stopwatch object and safely call any of the stopwatch methods.
*/
public class StopwatchImplementation implements IStopwatch {
  private enum StopwatchState {
    RUNNING,PAUSED,RESET
  }
  private final String id;
  private long startTime = 0;
  private List<Long> lapTimes = Collections.synchronizedList
      (new ArrayList<Long>());
  private StopwatchState state;
  private final Object lock = new Object();
  
  /**
   * Creates a new Stopwatch and assigns it's ID
   * @param id - The ID of the stopwatch
   * @throws IllegalArgumentException if <code>id</code> is empty or null 
   */
  public StopwatchImplementation(String id) {
    if(id == null || id.trim().equals("")){
      throw new IllegalArgumentException("Cannot give empty id!");
    }
    this.id = id;
    this.state = StopwatchState.RESET;
    this.startTime =  0;
  }

  /**
   * Returns the Id of this stopwatch
   * @return the Id of this stopwatch.  Will never be empty or null.
   */
  @Override
  public String getId() {
    return this.id;
  }

  /**
   * Starts the stopwatch.
   * @throws IllegalStateException if called when the stopwatch is already 
   * running
   */
  @Override
  public void start() {
  
    synchronized(lock){
      if(state == StopwatchState.RUNNING){
        throw new IllegalArgumentException("Stopwatch already running!");
      }
      
      //if watch is resumed after stopping, remove the lap counted as final 
      if(state == StopwatchState.PAUSED){
        lapTimes.remove(lapTimes.size()-1);
      }
      startTime = System.currentTimeMillis();
      state = StopwatchState.RUNNING;
    }
  }

  /**
   * Stores the time elapsed since the last time lap() was called
   * or since start() was called if this is the first lap.
   * @throws IllegalStateException if called when the stopwatch isn't running
   */
  @Override
  public void lap() {
      synchronized(lock){
      long currTime = System.currentTimeMillis();
      if(this.state != StopwatchState.RUNNING){
        throw new IllegalStateException("Stopwatch is not currently running!");
      }
      else {
       long lapTime = currTime-startTime;
       lapTimes.add(lapTime);
       startTime = currTime;
      }
    }
  }

  
  /**
   * Stops the stopwatch (and records one final lap).
   * @throws IllegalStateException if called when the stopwatch isn't running
   */
  @Override
  public void stop() {
  
    synchronized(lock){
      if(this.state != StopwatchState.RUNNING){
        throw new IllegalStateException("Stopwatch is not currently running!");
      }
      
      long currTime = System.currentTimeMillis();
      lapTimes.add(currTime-startTime);
      this.state = StopwatchState.PAUSED;
        
    }
  }

  /**
   * Resets the stopwatch. If the stopwatch is running, this method stops the
   * watch and resets it.  This also clears all recorded laps.
   */
  @Override
  public void reset() {

    synchronized(lock){
      startTime = 0;
      lapTimes.clear();
      state = StopwatchState.RESET;
    }
  }

  /**
   * Returns a list of lap times (in milliseconds).  This method can be called
   *  at any time and will not throw an exception.
   * @return a list of recorded lap times or an empty list if no times are 
   * recorded.
   */
  @Override
  public List<Long> getLapTimes() {
      return Collections.synchronizedList(lapTimes);
    
  }

  /**
   * Generates a unique hash code for the stopwatch object.
   * The hash code is generated using the id of the object.
   * @return a hash code value for this object.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    synchronized(lock){
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
    }
  }

  /**
   * Tests whether the given objects are equal. Two objects are considered equal
   * if their id's are the same, as id's are unique for each stopwatch object.
   * @return true if the objects are equal, else false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj){
      return true;
    }
    if (obj == null){
      return false;
    }
    if (!(obj instanceof StopwatchImplementation)){
      return false;
    }
    StopwatchImplementation other = (StopwatchImplementation) obj;
    if (id == null) {
      if (other.id != null){
        return false;
      }
    } else if (!id.equals(other.id)){
      return false;
    }
    return true;
  }
  
  
  /**
   * Provides the string representation of the stopwatch. This consists of the 
   * comma-separated lap times recorded by the stopwatch.
   * @return the string consisting of the lap times recorded by the stopwatch, 
   * an empty string if there are no laps recorded. 
   */
  @Override
  public String toString(){
    StringBuffer buffer = new StringBuffer();
    
    synchronized(lock){
      if(lapTimes.size()==0){
        return "";
      }
      for(Long lap:lapTimes){
        buffer.append(lap.toString()+",");
      }
      buffer.deleteCharAt(buffer.length()-1);
      return buffer.toString();
    }
  }
  
}
