package edu.nyu.mg3626.connectfour.view.logger;

import edu.nyu.mg3626.connectfour.view.ConnectFourListener;

/**
 * Log events of the ConnectFour game.
 * 
 * @author mg3626
 *
 */
public interface GameLogger extends ConnectFourListener {
  /**
   * Get the log as a string.
   * 
   * @return the log.
   */
  public String getLog();
}