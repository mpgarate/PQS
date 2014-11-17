package edu.nyu.mg3626.connectfour.view.logger;

import edu.nyu.mg3626.connectfour.model.Piece;

public class MultipleGameLogger implements GameLogger {

  MultipleGameLogger() {

  }

  StringBuilder sb = new StringBuilder();

  @Override
  public void gameStarted() {
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
    String log = sb.toString();
    sb = new StringBuilder();
    return log;
  }
}