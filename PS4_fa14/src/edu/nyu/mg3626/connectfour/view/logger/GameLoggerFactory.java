package edu.nyu.mg3626.connectfour.view.logger;

/**
 * Using the factory pattern, get an instance of a GameLogger given its type.
 * 
 * @author mg3626
 *
 */
public class GameLoggerFactory {
  /**
   * Get an instance of a GameLogger given its corresponding type.
   * 
   * @param gameLoggerType
   *          the GameLogger type
   * @return the GameLogger instance
   */
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
