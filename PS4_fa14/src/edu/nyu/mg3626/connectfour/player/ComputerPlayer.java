package edu.nyu.mg3626.connectfour.player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.nyu.mg3626.connectfour.IllegalMoveException;
import edu.nyu.mg3626.connectfour.model.ConnectFourModel;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.model.board.ConnectFourBoard;
import edu.nyu.mg3626.connectfour.model.board.MatrixBoardBuilder;

public class ComputerPlayer extends Player {

  public ComputerPlayer(String name, Color color) {
    super(name, color);
  }

  /**
   * Determine a single move that will result in a win. Otherwise, returns a
   * random column index.
   * 
   * @param model
   *          the game model from which the move should be predicted.
   * @return the column index for the winning move, a random move, or -1 for no
   *         moves possible for the given input.
   */
  public int getNextMove(ConnectFourModel model) {

    int numberOfColumns = model.getNumberOfColumns();

    List<Piece> moveHistory = model.getMoveHistory();
    List<Integer> legalMovesThatDoNotWin = new ArrayList<Integer>();

    ConnectFourBoard board;
    board = new MatrixBoardBuilder().withMoveHistory(moveHistory).build();

    if (board.gameIsOver()) {
      return -1;
    }

    for (int i = 0; i < numberOfColumns; i++) {

      board = new MatrixBoardBuilder().withMoveHistory(moveHistory).build();

      try {
        board.addPiece(this, i);
      } catch (IllegalMoveException illegalMoveException) {
        continue;
      }

      if (board.gameIsOver()) {
        return i;
      } else {
        legalMovesThatDoNotWin.add(i);
      }
    }

    Collections.shuffle(legalMovesThatDoNotWin);

    return legalMovesThatDoNotWin.get(0);
  }
}
