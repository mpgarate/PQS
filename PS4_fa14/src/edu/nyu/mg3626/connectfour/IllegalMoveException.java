package edu.nyu.mg3626.connectfour;

@SuppressWarnings("serial")
public class IllegalMoveException extends Exception {
  public IllegalMoveException() {
    super();
  }

  public IllegalMoveException(String message) {
    super(message);
  }
}
