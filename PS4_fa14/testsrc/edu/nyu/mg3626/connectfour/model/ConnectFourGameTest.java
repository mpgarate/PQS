package edu.nyu.mg3626.connectfour.model;

import static edu.nyu.mg3626.connectfour.util.TestUtil.RED_PLAYER;
import static edu.nyu.mg3626.connectfour.util.TestUtil.YELLOW_PLAYER;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.util.TestUtil;

public class ConnectFourGameTest {
  @Test
  public void testTiedGame() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      model.addPiece(piece.getColumnIndex());
    }

    assertTrue(model.gameIsOver());
  }
}
