package edu.nyu.mg3626.connectfour.model;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.board.ConnectFourBoard;
import edu.nyu.mg3626.connectfour.model.board.MatrixBoardBuilder;
import edu.nyu.mg3626.connectfour.player.Player;
import edu.nyu.mg3626.connectfour.view.ConnectFourListener;

public class ConnectFourGame implements ConnectFourModel {
  private final int numberOfColumns;
  private final int numberOfRows;
  private final int victoryConnectionSize;

  private ConnectFourBoard board;
  private Player currentTurnPlayer;

  private Player player1;
  private Player player2;

  private List<ConnectFourListener> listeners =
      new ArrayList<ConnectFourListener>();

  public ConnectFourGame() {
    this(GameConstants.NUM_COLUMNS, GameConstants.NUM_ROWS,
        GameConstants.VICTORY_CONNECTION_SIZE);
  }

  private ConnectFourGame(int numberOfColumns, int numberOfRows,
      int victoryConnectionSize) {
    this.numberOfColumns = numberOfColumns;
    this.numberOfRows = numberOfRows;
    this.victoryConnectionSize = victoryConnectionSize;
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public void startNewGame(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;

    // player 1 starts
    currentTurnPlayer = player1;

    board = new MatrixBoardBuilder().build();

    fireGameStartedEvent();
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public void addPiece(int columnIndex) throws IllegalMoveException {

    Player player = currentTurnPlayer;

    Piece piece = board.addPiece(player, columnIndex);

    currentTurnPlayer = player.equals(player1) ? player2 : player1;

    if (board.gameIsOver()) {
      Player winningPlayer = board.getWinner();

      if (null == winningPlayer) {
        fireGameTiedEvent(piece);
      } else {
        fireGameWonEvent(piece);
      }
    } else {
      firePieceAddedEvent(piece);
    }
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public List<Piece> getMoveHistory() {
    return board.getMoveHistory();
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public void addConnectFourListener(ConnectFourListener listener) {
    listeners.add(listener);
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public int getNumberOfColumns() {
    return numberOfColumns;
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public int getNumberOfRows() {
    return numberOfRows;
  }

  @Override
  /**
   * {@inheritDoc}
   */
  public boolean gameIsOver() {
    return null == board || board.gameIsOver();
  }

  private void fireGameStartedEvent() {
    for (ConnectFourListener listener : listeners) {
      listener.gameStarted();
    }
  }

  private void fireGameWonEvent(Piece piece) {
    for (ConnectFourListener listener : listeners) {
      listener.gameWon(piece);
    }
  }

  private void fireGameTiedEvent(Piece piece) {
    for (ConnectFourListener listener : listeners) {
      listener.gameTied(piece);
    }
  }

  private void firePieceAddedEvent(Piece piece) {
    for (ConnectFourListener listener : listeners) {
      listener.pieceAdded(piece);
    }
  }

  @Override
  public int getVictoryConnectionSize() {
    return victoryConnectionSize;
  }

  public String toString() {
    return board.toString();
  }
}
