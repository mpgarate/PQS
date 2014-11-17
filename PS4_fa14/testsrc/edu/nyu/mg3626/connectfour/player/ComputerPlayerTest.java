package edu.nyu.mg3626.connectfour.player;

import static edu.nyu.mg3626.connectfour.util.TestUtil.YELLOW_PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.model.board.ConnectFourBoard;
import edu.nyu.mg3626.connectfour.model.board.MatrixBoardBuilder;
import edu.nyu.mg3626.connectfour.util.TestUtil;

public class ComputerPlayerTest {
  @Test
  public void testGetNextMove_unknownFailingCase() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    ComputerPlayer player1 = new ComputerPlayer("Red", Color.RED);
    HumanPlayer player2 = new HumanPlayer("Yellow", Color.YELLOW);

    model.startNewGame(player1, player2);

    // red
    model.addPiece(3);
    // yellow
    model.addPiece(3);
    // red
    model.addPiece(4);
    // yellow
    model.addPiece(3);
    // red
    model.addPiece(5);
    // yellow
    model.addPiece(3);

    int nextMove = player1.getNextMove(model);

    model.addPiece(nextMove);

    ConnectFourBoard board =
        new MatrixBoardBuilder().withMoveHistory(model.getMoveHistory())
            .build();

    assertEquals(player1, board.getWinner());
  }

  @Test
  public void testGetNextMove_boardFull() throws IllegalMoveException {
    ComputerPlayer computerPlayer = new ComputerPlayer("Red", Color.RED);

    ConnectFourModel model = new ConnectFourGame();
    model.startNewGame(computerPlayer, YELLOW_PLAYER);

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      model.addPiece(piece.getColumnIndex());
    }

    assertEquals(-1, computerPlayer.getNextMove(model));
  }

  @Test
  public void testGetNextMove_noWinningMovesAvailableReturnsRandom() {
    ComputerPlayer computerPlayer = new ComputerPlayer("Red", Color.RED);

    ConnectFourModel model = new ConnectFourGame();
    model.startNewGame(computerPlayer, YELLOW_PLAYER);

    int nextMove = computerPlayer.getNextMove(model);

    assertTrue(nextMove >= 0 && nextMove < GameConstants.NUM_COLUMNS);
  }

  @Test
  public void testGetNextMove_oneOfTheGuessedMovesWillBeInvalid()
      throws IllegalMoveException {
    ComputerPlayer computerPlayer = new ComputerPlayer("Red", Color.RED);

    ConnectFourModel model = new ConnectFourGame();
    model.startNewGame(computerPlayer, YELLOW_PLAYER);

    // fill column 0
    model.addPiece(0);
    model.addPiece(0);
    model.addPiece(0);
    model.addPiece(0);
    model.addPiece(0);
    model.addPiece(0);

    // fill column 1
    model.addPiece(1);
    model.addPiece(1);
    model.addPiece(1);
    model.addPiece(1);
    model.addPiece(1);
    model.addPiece(1);

    int nextMove = computerPlayer.getNextMove(model);

    assertTrue(nextMove >= 0 && nextMove < GameConstants.NUM_COLUMNS);
  }
}
