package edu.nyu.mg3626.connectfour.util;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import edu.nyu.mg3626.connectfour.GameConstants;
import edu.nyu.mg3626.connectfour.model.Piece;
import edu.nyu.mg3626.connectfour.player.HumanPlayer;
import edu.nyu.mg3626.connectfour.player.Player;

public final class TestUtil {

  public final static Player RED_PLAYER = new HumanPlayer("Red", Color.RED);
  public final static Player YELLOW_PLAYER = new HumanPlayer("Yel",
      Color.YELLOW);

  public static List<Piece> getMovesForFullAndTiedGame() {
    List<Piece> movesToMake = new LinkedList<Piece>();
    List<Piece> finalMoves = new LinkedList<Piece>();

    Player previousPlayer = YELLOW_PLAYER;

    for (int j = 0; j < GameConstants.NUM_COLUMNS; j++) {
      for (int i = 0; i < GameConstants.NUM_ROWS; i++) {
        Player currentPlayer =
            RED_PLAYER.equals(previousPlayer) ? YELLOW_PLAYER : RED_PLAYER;

        Piece piece = new Piece(currentPlayer, 0, j);

        if (j > 0 && j % 2 == 0 && i == GameConstants.NUM_ROWS - 1) {
          finalMoves.add(piece);
          continue;
        }

        previousPlayer = currentPlayer;
        movesToMake.add(piece);
      }
    }

    for (Piece piece : finalMoves) {
      movesToMake.add(piece);
    }

    /*
     * for (int j = 0; j < GameConstants.NUM_COLUMNS; j++) { if
     * (columnsOfPlayerStart == 3) { previousPlayer =
     * previousPlayer.equals(RED_PLAYER) ? YELLOW_PLAYER : RED_PLAYER;
     * columnsOfPlayerStart = 0; } else { columnsOfPlayerStart++; }
     * 
     * for (int i = 0; i < GameConstants.NUM_ROWS; i++) { Player player =
     * previousPlayer.equals(RED_PLAYER) ? YELLOW_PLAYER : RED_PLAYER;
     * 
     * previousPlayer = player;
     * 
     * movesToMake.add(new Piece(player, 0, j)); } }
     */

    return movesToMake;
  }
}
