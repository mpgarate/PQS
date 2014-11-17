package edu.nyu.mg3626.connectfour.view;

import static edu.nyu.mg3626.connectfour.util.TestUtil.RED_PLAYER;
import static edu.nyu.mg3626.connectfour.util.TestUtil.YELLOW_PLAYER;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.ConnectFourGame;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.util.TestUtil;
import edu.nyu.mg3626.connectfour.view.logger.GameLogger;
import edu.nyu.mg3626.connectfour.view.logger.GameLoggerFactory;
import edu.nyu.mg3626.connectfour.view.logger.GameLoggerType;

public class GameLoggerTest {
  @Test
  public void testMultipleGameLogger_recordsAcrossMultipleGames()
      throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.MULTIPLE_GAME);

    model.addConnectFourListener(logger);

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    model.addPiece(3);

    model.startNewGame(YELLOW_PLAYER, RED_PLAYER);

    model.addPiece(1);

    String expectedMessage1 = "pieceAdded(Red : [0, 3])";
    String expectedMessage2 = "pieceAdded(Yel : [0, 1])";

    String log = logger.getLog();

    assertThat(log, containsString(expectedMessage1));
    assertThat(log, containsString(expectedMessage2));
  }

  @Test
  public void testSingleGameLogger_recordsOnlyLastGame()
      throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    GameLogger logger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);

    model.addConnectFourListener(logger);

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    model.addPiece(3);

    model.startNewGame(YELLOW_PLAYER, RED_PLAYER);

    model.addPiece(1);

    String expectedMessage1 = "pieceAdded(Red : [0, 3])";
    String expectedMessage2 = "pieceAdded(Yel : [0, 1])";

    String log = logger.getLog();

    assertThat(log, not(containsString(expectedMessage1)));
    assertThat(log, containsString(expectedMessage2));
  }

  @Test
  public void testGameLogsReceiveGameWonEvent() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    GameLogger multiLogger =
        GameLoggerFactory.getInstance(GameLoggerType.MULTIPLE_GAME);
    GameLogger singleLogger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);

    model.addConnectFourListener(singleLogger);
    model.addConnectFourListener(multiLogger);

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);
    model.addPiece(1);
    model.addPiece(0);

    String singleLog = singleLogger.getLog();
    String multiLog = multiLogger.getLog();

    String expectedMessage = "gameWon(Red : [3, 0])";

    assertThat(multiLog, containsString(expectedMessage));
    assertThat(singleLog, containsString(expectedMessage));
  }

  @Test
  public void testGameLogsReceiveGameTiedEvent() throws IllegalMoveException {
    ConnectFourModel model = new ConnectFourGame();

    GameLogger multiLogger =
        GameLoggerFactory.getInstance(GameLoggerType.MULTIPLE_GAME);
    GameLogger singleLogger =
        GameLoggerFactory.getInstance(GameLoggerType.SINGLE_GAME);

    model.addConnectFourListener(singleLogger);
    model.addConnectFourListener(multiLogger);

    model.startNewGame(RED_PLAYER, YELLOW_PLAYER);

    for (Piece piece : TestUtil.getMovesForFullAndTiedGame()) {
      model.addPiece(piece.getColumnIndex());
    }

    String singleLog = singleLogger.getLog();
    String multiLog = multiLogger.getLog();

    String expectedMessage = "gameTied(Yel : [5, 6])";

    assertThat(multiLog, containsString(expectedMessage));
    assertThat(singleLog, containsString(expectedMessage));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGameLoggerFactory_throwsExceptionForNullParameter() {
    GameLoggerFactory.getInstance(null);
  }
}
