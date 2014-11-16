package edu.nyu.mg3626.connectfour.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.player.Player;

public class MatrixBoard implements ConnectFourBoard {
  private final int numberOfColumns;
  private final int numberOfRows;
  private final int victoryConnectionSize;

  private Piece[][] pieces;

  private List<Piece> moveHistory;

  private Player winner;
  private boolean gameIsStarted = false;

  // TODO: use builder pattern

  public MatrixBoard() {
    this(GameConstants.NUM_COLUMNS, GameConstants.NUM_ROWS,
        GameConstants.VICTORY_CONNECTION_SIZE);
  }

  // TODO: some constructions are impossible, such as using a custom board size
  // in the board used to generate the piecesList
  public MatrixBoard(List<Piece> piecesList) throws IllegalMoveException {
    this();

    for (Piece piece : piecesList) {
      addPiece(piece.getPlayer(), piece.getColumnIndex());
    }
  }

  public MatrixBoard(int numberOfColumns, int numberOfRows,
      int victoryConnectionSize) {
    this.numberOfColumns = numberOfColumns;
    this.numberOfRows = numberOfRows;
    this.victoryConnectionSize = victoryConnectionSize;

    pieces = new Piece[numberOfRows][numberOfColumns];
    moveHistory = new LinkedList<Piece>();
    winner = null;
    gameIsStarted = true;
  }

  /**
   * 
   * Get the index of the next empty space for a given column. Returns when
   * column is full.
   * 
   * @param column
   *          the column to check
   * @return the index of the free space in the column or -1 if none found
   */
  private int findIndexOfNextEmptySpaceInColumn(int columnIndex) {
    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      if (pieces[rowIndex][columnIndex] == null) {
        return rowIndex;
      }
    }

    return -1;
  }

  public Piece addPiece(Player player, int columnIndex)
      throws IllegalMoveException {

    if (!gameIsStarted) {
      throw new IllegalMoveException("There is no active game.");
    }

    if (columnIndex >= numberOfColumns || columnIndex < 0) {
      throw new IllegalMoveException("Column out of range.");
    }

    if (pieces[numberOfRows - 1][columnIndex] != null) {
      throw new IllegalMoveException("Column is full.");
    }

    int rowIndex = findIndexOfNextEmptySpaceInColumn(columnIndex);

    Piece piece = new Piece(player, rowIndex, columnIndex);

    pieces[rowIndex][columnIndex] = piece;
    moveHistory.add(piece);

    if (gameIsOverFor(piece)) {
      winner = piece.getPlayer();
      gameIsStarted = false;
    } else if (boardIsFull()) {
      gameIsStarted = false;
    }

    return piece;
  }

  private boolean gameIsOverFor(Piece piece) {
    if (hasVerticalWin(piece) || hasHorizontalWin(piece)
        || hasDiagonalWin(piece)) {
      return true;
    }

    return false;
  }

  private boolean boardIsFull() {
    for (int i = 0; i < numberOfColumns; i++) {
      if (null == pieces[numberOfRows - 1][i]) {
        return false;
      }
    }

    return true;
  }

  private boolean hasDiagonalWin(Piece lastMove) {

    int streak = 0;

    // when i == 0, checking in the direction from SW to NE
    // when i == 1, checking in the direction from NW to SE
    for (int i = 0; i < 2; i++) {
      int offset = 0 - (victoryConnectionSize - 1);
      for (; offset < victoryConnectionSize; offset++) {

        // invert row offset when checking from NW to SE
        int rowOffset = i == 0 ? offset : offset * -1;

        int row = lastMove.getRowIndex() + rowOffset;
        int column = lastMove.getColumnIndex() + offset;

        // if either index is out of bounds
        if (row > numberOfRows - 1 || column > numberOfColumns - 1 || row < 0
            || column < 0) {
          streak = 0;
          continue;
        }

        Piece piece = pieces[row][column];
        if (null == piece || !piece.getPlayer().equals(lastMove.getPlayer())) {
          streak = 0;
        } else {
          streak++;
        }

        if (streak == victoryConnectionSize) {
          return true;
        }
      }
    }

    return false;
  }

  private boolean hasHorizontalWin(Piece lastMove) {

    int streak = 0;
    int rowIndex = lastMove.getRowIndex();

    for (int i = 0; i < numberOfColumns; i++) {
      Piece piece = pieces[rowIndex][i];

      if (null == piece || !piece.getPlayer().equals(lastMove.getPlayer())) {
        streak = 0;
      } else {
        streak++;
      }

      if (streak == victoryConnectionSize) {
        return true;
      }
    }

    return false;
  }

  private boolean hasVerticalWin(Piece lastMove) {

    if (lastMove.getRowIndex() - victoryConnectionSize < -1) {
      return false;
    }

    int columnIndex = lastMove.getColumnIndex();
    int streak = 0;

    for (int i = lastMove.getRowIndex(); i >= 0; i--) {
      Piece piece = pieces[i][columnIndex];

      if (piece.getPlayer().equals(lastMove.getPlayer())) {
        streak++;
      } else {
        streak = 0;
      }

      if (streak == victoryConnectionSize) {
        return true;
      }
    }

    return false;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = pieces.length - 1; i >= 0; i--) {
      for (int j = 0; j < pieces[i].length; j++) {
        Piece piece = pieces[i][j];
        sb.append(i + ", " + j + " : ");
        if (null == piece) {
          sb.append("null, ");
        } else {
          sb.append(piece.getPlayer());
          sb.append(", ");
        }
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  @Override
  public boolean gameIsOver() {
    return !gameIsStarted;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public List<Piece> getMoveHistory() {
    List<Piece> piecesList = new ArrayList<Piece>(moveHistory.size());

    for (Piece piece : moveHistory) {
      piecesList.add(piece);
    }

    return piecesList;
  }
}
