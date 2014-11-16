package edu.nyu.mg3626.connectfour.model;

import java.util.List;

import edu.nyu.mg3626.connectfour.ConnectFourListener;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.player.Player;

public interface ConnectFourModel {

  /**
   * Add a listener to this model. Each listener will be notified about events
   * in the progress of the game. @see ConnectFourListener
   * 
   * @param listener
   */
  public void addConnectFourListener(ConnectFourListener listener);

  public void addPiece(int columnIndex) throws IllegalMoveException;

  /**
   * Start a new game.
   * 
   * @param player1
   * @param player2
   */
  public void startNewGame(Player player1, Player player2);

  /**
   * Get the ordered history of moves made in this game.
   * 
   * @return the ordered list of pieces moved
   */
  public List<Piece> getMoveHistory();

  /**
   * Get the number of columns considered in this game.
   * 
   * @return the number of columns
   */
  public int getNumberOfColumns();

  /**
   * Get the number of rows considered in this game.
   * 
   * @return the number of rows
   */
  public int getNumberOfRows();

  /**
   * Determine if the game is over or currently active.
   * 
   * @return true if the game is over, false if the game is ongoing.
   */
  public boolean gameIsOver();
}
