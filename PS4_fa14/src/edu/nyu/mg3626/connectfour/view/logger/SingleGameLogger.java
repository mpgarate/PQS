package edu.nyu.mg3626.connectfour.view.logger;

import edu.nyu.mg3626.connectfour.model.Piece;

/**
 * Log events of a single Connect Four game. Events are cleared upon receipt of
 * the gameStarted() event.
 * 
 * @author mg3626
 *
 */
public class SingleGameLogger implements GameLogger {

  SingleGameLogger() {

  }

  StringBuilder sb = new StringBuilder();

  @Override
  public void gameStarted() {
    sb = new StringBuilder();
    sb.append("gameStarted()");
    sb.append("\n");
  }

  @Override
  public void pieceAdded(Piece piece) {
    sb.append("pieceAdded(" + piece + ")");
    sb.append("\n");
  }

  @Override
  public void gameWon(Piece piece) {
    sb.append("gameWon(" + piece + ")");
    sb.append("\n");
  }

  @Override
  public void gameTied(Piece piece) {
    sb.append("gameTied(" + piece + ")");
    sb.append("\n");
  }

  /**
   * Get the log as a String
   * 
   * @return the log
   */
  public String getLog() {
    return sb.toString();
  }

}
