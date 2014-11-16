package edu.nyu.mg3626.connectfour.player;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.ConnectFourBoard;
import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.model.MatrixBoard;

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

    ConnectFourBoard board = new MatrixBoard(model.getMoveHistory());

    assertEquals(player1, board.getWinner());
  }

  @Test
  public void testGetNextMove_boardFull() {

  }
}
