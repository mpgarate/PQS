package edu.nyu.mg3626.connectfour;

import edu.nyu.mg3626.connectfour.model.Piece;

public interface ConnectFourListener {

  /**
   * Handle event for the beginning of a new game.
   */
  void gameStarted();

  /**
   * Handle event when a new piece is played in the game. This event does not
   * occur if the piece ended the game.
   * 
   * @param piece
   *          the piece that was played
   */
  void pieceAdded(Piece piece);

  /**
   * Handle event when a move wins the game.
   * 
   * @param piece
   *          the piece that won the game
   */
  void gameWon(Piece piece);

  /**
   * Handle event when a move ties the game.
   * 
   * @param piece
   *          the piece that ended the game.
   */
  void gameTied(Piece piece);
}
