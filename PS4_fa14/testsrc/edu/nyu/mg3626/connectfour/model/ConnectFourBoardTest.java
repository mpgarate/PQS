package edu.nyu.mg3626.connectfour.model;

import static edu.nyu.mg3626.connectfour.util.TestUtil.RED_PLAYER;
import static edu.nyu.mg3626.connectfour.util.TestUtil.YELLOW_PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.util.TestUtil;

public class ConnectFourBoardTest {

  @Test(expected = IllegalMoveException.class)
  public void testAddPiece_fillAColumn() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();
    for (int i = 0; i < GameConstants.NUM_ROWS; i++) {
      board.addPiece(RED_PLAYER, 1);
      board.addPiece(YELLOW_PLAYER, 1);
    }
  }

  @Test(expected = IllegalMoveException.class)
  public void testAddPiece_tooHighColumn() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();
    board.addPiece(RED_PLAYER, 9999);
  }

  @Test(expected = IllegalMoveException.class)
  public void testAddPiece_tooLowColumn() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();
    board.addPiece(RED_PLAYER, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddPiece_nullColor() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();
    board.addPiece(null, 1);
  }

  @Test
  public void testAddPiece_verticalWin() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 4);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 1);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 4);
    board.addPiece(RED_PLAYER, 3);

    assertTrue(board.gameIsOver());
    assertEquals(RED_PLAYER, board.getWinner());
  }

  @Test
  public void testAddPiece_horizontalWin() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(YELLOW_PLAYER, 0);
    board.addPiece(RED_PLAYER, 0);
    board.addPiece(YELLOW_PLAYER, 1);
    board.addPiece(RED_PLAYER, 1);
    board.addPiece(YELLOW_PLAYER, 2);
    board.addPiece(RED_PLAYER, 2);
    board.addPiece(YELLOW_PLAYER, 3);

    assertTrue(board.gameIsOver());
    assertEquals(YELLOW_PLAYER, board.getWinner());
  }

  @Test
  public void testAddPiece_horizontalWinColTreeThroughSix()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();
    board.addPiece(YELLOW_PLAYER, 3);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 4);
    board.addPiece(RED_PLAYER, 4);
    board.addPiece(YELLOW_PLAYER, 5);
    board.addPiece(RED_PLAYER, 5);
    board.addPiece(YELLOW_PLAYER, 6);

    assertTrue(board.gameIsOver());
    assertEquals(YELLOW_PLAYER, board.getWinner());
  }

  @Test
  public void testAddPiece_diagonalSouthWestToNorthEastWin()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(YELLOW_PLAYER, 0);
    board.addPiece(RED_PLAYER, 1);
    board.addPiece(YELLOW_PLAYER, 1);
    board.addPiece(RED_PLAYER, 4);
    board.addPiece(YELLOW_PLAYER, 2);
    board.addPiece(RED_PLAYER, 2);
    board.addPiece(YELLOW_PLAYER, 2);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 2);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 3);
    board.addPiece(RED_PLAYER, 4);
    board.addPiece(YELLOW_PLAYER, 3);

    assertTrue(board.gameIsOver());
    assertEquals(YELLOW_PLAYER, board.getWinner());
  }

  @Test
  public void testAddPiece_diagonalNorthWestToSouthEastWin()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(YELLOW_PLAYER, 5);
    board.addPiece(RED_PLAYER, 4);
    board.addPiece(YELLOW_PLAYER, 4);
    board.addPiece(RED_PLAYER, 5);
    board.addPiece(YELLOW_PLAYER, 3);
    board.addPiece(RED_PLAYER, 2);
    board.addPiece(YELLOW_PLAYER, 3);
    board.addPiece(RED_PLAYER, 4);
    board.addPiece(YELLOW_PLAYER, 3);
    board.addPiece(RED_PLAYER, 2);
    board.addPiece(YELLOW_PLAYER, 2);
    board.addPiece(RED_PLAYER, 1);
    board.addPiece(YELLOW_PLAYER, 2);

    assertTrue(board.gameIsOver());
    assertEquals(YELLOW_PLAYER, board.getWinner());
  }

  @Test(expected = IllegalMoveException.class)
  public void addPiece_afterGameOverThrowsException()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(RED_PLAYER, 0);
    board.addPiece(YELLOW_PLAYER, 1);
    board.addPiece(RED_PLAYER, 0);
    board.addPiece(YELLOW_PLAYER, 1);
    board.addPiece(RED_PLAYER, 0);
    board.addPiece(YELLOW_PLAYER, 1);

    // red's winning move
    board.addPiece(RED_PLAYER, 0);

    // yellow should not be allowed to move
    board.addPiece(YELLOW_PLAYER, 1);
  }

  @Test
  public void addPiece_forFullBoardIsATie() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      board.addPiece(piece.getPlayer(), piece.getColumnIndex());
    }

    assertTrue(board.gameIsOver());
    assertEquals(null, board.getWinner());
  }

  @Test
  public void addPiece_forFailingCaseIncorrectBoardIsFullDetected()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    int[] redMoves = { 1, 4, 5, 1, 3, 5, 0, 1, 6, 6, 1, 0, 2 };
    int[] yellowMoves = { 0, 3, 6, 0, 4, 6, 3, 5, 0, 1, 6, 6, 0, 1 };

    board.addPiece(YELLOW_PLAYER, yellowMoves[0]);
    for (int i = 0; i < redMoves.length; i++) {
      board.addPiece(RED_PLAYER, redMoves[i]);
      board.addPiece(YELLOW_PLAYER, yellowMoves[i + 1]);
    }

    assertFalse(board.gameIsOver());
  }

  @Test
  public void addPiece_recognizesDiagonalSWToNEFromRightEdge()
      throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    int[] redMoves = { 6, 5, 4, 6, 5, 2 };
    int[] yellowMoves = { 6, 5, 5, 3, 4, 6 };

    for (int i = 0; i < redMoves.length; i++) {
      board.addPiece(RED_PLAYER, redMoves[i]);
      board.addPiece(YELLOW_PLAYER, yellowMoves[i]);
    }

    assertTrue(board.gameIsOver());
    assertEquals(YELLOW_PLAYER, board.getWinner());
  }

  @Test
  public void testGameIsOver_canBeFalse() {
    ConnectFourBoard board = new MatrixBoard();
    assertFalse(board.gameIsOver());
  }

  @Test
  public void testGetPieceHistory() throws IllegalMoveException {
    ConnectFourBoard board = new MatrixBoard();

    board.addPiece(RED_PLAYER, 0);
    board.addPiece(YELLOW_PLAYER, 4);
    board.addPiece(RED_PLAYER, 3);
    board.addPiece(YELLOW_PLAYER, 1);

    List<Piece> moveHistory = board.getMoveHistory();

    assertEquals(RED_PLAYER, moveHistory.get(0).getPlayer());
    assertTrue(0 == moveHistory.get(0).getColumnIndex());

    assertEquals(YELLOW_PLAYER, moveHistory.get(1).getPlayer());
    assertTrue(4 == moveHistory.get(1).getColumnIndex());

    assertEquals(RED_PLAYER, moveHistory.get(2).getPlayer());
    assertTrue(3 == moveHistory.get(2).getColumnIndex());

    assertEquals(YELLOW_PLAYER, moveHistory.get(3).getPlayer());
    assertTrue(1 == moveHistory.get(3).getColumnIndex());
  }
}
