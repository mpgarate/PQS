package edu.nyu.mg3626.connectfour.view.logger;

public class GameLoggerFactory {
  public static GameLogger getInstance(GameLoggerType gameLoggerType) {

    if (null == gameLoggerType) {
      throw new IllegalArgumentException("Must provide a GameLoggerType");
    }

    switch (gameLoggerType) {
    case MULTIPLE_GAME:
      return new MultipleGameLogger();
    case SINGLE_GAME:
      return new SingleGameLogger();
    default:
      return null;
    }
  }
}
