package edu.nyu.mg3626.connectfour.model.board;

import java.util.LinkedList;
import java.util.List;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.model.Piece;

/**
 * Builder for the MatrixBoard class. Allows for optionally providing a
 * moveHistory list of events with which to seed the board. This builder is also
 * responsible for supplying the grid dimensions (numberOfColumns and
 * numberOfRows) as well as the victoryConnectionSize. Although these values now
 * are set via static GameConstants, a future implementation could use this
 * pattern to allow for dynamically setting dimensions.
 * 
 * @author mg3626
 *
 */
public class MatrixBoardBuilder {

  private List<Piece> moveHistory = new LinkedList<Piece>();
  private final int numberOfColumns = GameConstants.NUM_COLUMNS;
  private final int numberOfRows = GameConstants.NUM_ROWS;
  private final int victoryConnectionSize =
      GameConstants.VICTORY_CONNECTION_SIZE;

  /**
   * Construct this builder.
   */
  public MatrixBoardBuilder() {

  }

  /**
   * Provide a move history with which to initialize this board. Moves will be
   * applied in the order of the list. If any move is invalid during playback of
   * this history, the playback will be reset an empty board will be created
   * instead.
   * 
   * @param moveHistory
   * @return the builder
   */
  public MatrixBoardBuilder withMoveHistory(List<Piece> moveHistory) {
    this.moveHistory = moveHistory;
    return this;
  }

  /**
   * Build the MatrixBoard instance
   * 
   * @return the MatrixBoard instance
   */
  public MatrixBoard build() {
    MatrixBoard board = new MatrixBoard(this);
    return board;
  }

  List<Piece> getMoveHistory() {
    return moveHistory;
  }

  int getNumberOfRows() {
    return numberOfRows;
  }

  int getNumberOfColumns() {
    return numberOfColumns;
  }

  int getVictoryConnectionSize() {
    return victoryConnectionSize;
  }
}
