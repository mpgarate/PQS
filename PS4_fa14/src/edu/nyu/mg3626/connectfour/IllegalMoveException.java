package edu.nyu.mg3626.connectfour;

/**
 * A checked exception for invalid attempts to place a piece in the game.
 * 
 * @author mg3626
 *
 */
@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {
  /**
   * Throw the exception
   */
  public IllegalMoveException() {
    super();
  }

  /**
   * Throw the exception with an explanatory message describing the causing
   * condition.
   * 
   * @param message
   *          the explanation of the cuase for this exception
   */
  public IllegalMoveException(String message) {
    super(message);
  }
}
