package edu.nyu.mg3626.connectfour.model.board;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.player.Player;

public class MatrixBoard implements ConnectFourBoard {
  private final int numberOfColumns;
  private final int numberOfRows;
  private final int victoryConnectionSize;

  private Piece[][] pieces;

  private List<Piece> moveHistory;

  private Player winner;
  private boolean gameIsStarted = false;

  protected MatrixBoard(MatrixBoardBuilder builder) {
    this.numberOfColumns = builder.getNumberOfColumns();
    this.numberOfRows = builder.getNumberOfRows();
    this.victoryConnectionSize = builder.getVictoryConnectionSize();

    List<Piece> moveHistoryToLoad = builder.getMoveHistory();

    clearAndInitializeGameBoard();

    loadMoveHistory(moveHistoryToLoad);
  }

  private void clearAndInitializeGameBoard() {
    pieces = new Piece[numberOfRows][numberOfColumns];
    this.moveHistory = new LinkedList<Piece>();
    winner = null;
    gameIsStarted = true;
  }

  private void loadMoveHistory(List<Piece> moveHistory) {
    try {
      for (Piece piece : moveHistory) {
        addPiece(piece.getPlayer(), piece.getColumnIndex());
      }
    } catch (IllegalMoveException | IllegalArgumentException e) {
      clearAndInitializeGameBoard();
    }
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

    int nextEmptySpace = -1;

    for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
      if (pieces[rowIndex][columnIndex] == null) {
        nextEmptySpace = rowIndex;
        break;
      }
    }

    return nextEmptySpace;
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

    String gameState = gameIsStarted ? "Ongoing game" : "No active game";

    String boardWinner;

    if (null == winner) {
      boardWinner = "No winner";
    } else {
      boardWinner = "Winner is " + winner.getName();
    }

    String moveCount = moveHistory.size() + " moves made. ";

    sb.append("MatrixBoard: ");
    sb.append(gameState + " | ");
    sb.append(boardWinner + " | ");
    sb.append(moveCount);

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
