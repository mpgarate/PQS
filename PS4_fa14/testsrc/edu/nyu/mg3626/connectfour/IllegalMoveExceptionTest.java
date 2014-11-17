package edu.nyu.mg3626.connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IllegalMoveExceptionTest {
  @Test
  public void testThrowIllegalMoveException_withMessage() {
    String message = "Lorem ipsum dolor sit amet";

    String foundMessage = null;

    try {
      throw new IllegalMoveException(message);
    } catch (IllegalMoveException e) {
      foundMessage = e.getMessage();
    }

    assertEquals(message, foundMessage);
  }

  @Test
  public void testThrowIllegalMoveException_withoutMessage() {
    boolean caughtException = false;

    try {
      throw new IllegalMoveException();
    } catch (IllegalMoveException e) {
      caughtException = true;
    }

    assertTrue(caughtException);
  }
}
