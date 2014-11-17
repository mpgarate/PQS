package edu.nyu.mg3626.connectfour.model.board;

import java.util.List;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.player.Player;

public interface ConnectFourBoard {

  Piece addPiece(Player player, int columnIndex) throws IllegalMoveException;

  /**
   * Determine if the game represented by this board is over or currently
   * active.
   * 
   * @return true if the game is over, false if the game is ongoing.
   */
  boolean gameIsOver();

  /**
   * Get the ordered history of moves made in this board.
   * 
   * @return the ordered list of pieces moved
   */
  List<Piece> getMoveHistory();

  /**
   * Get the winning player. A null value means that no player has won. If
   * gameIsOver() == true and getWinner() returns null, the game is a tie.
   * 
   * @return the winning player or null if no player has won.
   */
  Player getWinner();

}
