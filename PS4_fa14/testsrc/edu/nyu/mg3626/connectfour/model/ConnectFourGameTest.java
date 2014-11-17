package edu.nyu.mg3626.connectfour.model;

import static edu.nyu.mg3626.connectfour.util.TestUtil.RED_PLAYER;
import static edu.nyu.mg3626.connectfour.util.TestUtil.YELLOW_PLAYER;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.util.TestUtil;
import edu.nyu.mg3626.connectfour.view.logger.GameLogger;
import edu.nyu.mg3626.connectfour.view.logger.GameLoggerFactory;
import edu.nyu.mg3626.connectfour.view.logger.GameLoggerType;

public class ConnectFourGameTest {

  @Test
  public void testConstructGameSetsDefaultConstants() {
    ConnectFourModel model = new ConnectFourGame();

    assertEquals(GameConstants.NUM_ROWS, model.getNumberOfRows());
    assertEquals(GameConstants.NUM_COLUMNS, model.getNumberOfColumns());
    assertEquals(GameConstants.VICTORY_CONNECTION_SIZE,
        model.getVictoryConnectionSize());
  }

  @Test
  public void testTiedGame() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      model.addPiece(piece.getColumnIndex());
    }

    assertTrue(model.gameIsOver());
  }

  @Test
  public void testAddConnectFourListener_gameStartedEvent() {
    ConnectFourModel model = new ConnectFourGame();
    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);
    model.addConnectFourListener(logger);

    String expectedMessage = "gameStarted()";

    assertThat(logger.getLog(), not(containsString(expectedMessage)));
    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    assertThat(logger.getLog(), containsString(expectedMessage));
  }

  @Test
  public void testAddConnectFourListener_pieceAddedEvent()
      throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();
    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);
    model.addConnectFourListener(logger);

    String expectedMessage = "pieceAdded(Red : [0, 0])";

    assertThat(logger.getLog(), not(containsString(expectedMessage)));

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);
    model.addPiece(0);

    assertThat(logger.getLog(), containsString(expectedMessage));
  }

  @Test
  public void testAddConnectFourListener_gameWonEvent()
      throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();
    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);
    model.addConnectFourListener(logger);

    String expectedMessage = "gameWon(Red : [3, 0])";
    assertThat(logger.getLog(), not(containsString(expectedMessage)));

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);

    assertThat(logger.getLog(), containsString(expectedMessage));
  }

  @Test
  public void testAddConnectFourListener_gameTiedEvent()
      throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();
    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);
    model.addConnectFourListener(logger);

    String expectedMessage = "gameTied(Yel : [5, 6])";
    assertThat(logger.getLog(), not(containsString(expectedMessage)));

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      model.addPiece(piece.getColumnIndex());
    }

    assertThat(logger.getLog(), containsString(expectedMessage));
  }

  @Test
  public void testGameIsOver_withNoActivegame() {
    ConnectFourModel model = new ConnectFourGame();

    assertTrue(model.gameIsOver());
  }

  @Test
  public void TestGameIsOver_falseWithOngoingGame() {
    ConnectFourModel model = new ConnectFourGame();
    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    assertFalse(model.gameIsOver());
  }

  @Test
  public void testGameIsOver_whenGameHasBeenWon() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);

    assertTrue(model.gameIsOver());
  }
}
