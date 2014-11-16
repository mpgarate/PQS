package edu.nyu.mg3626.connectfour.model;

import edu.nyu.mg3626.connectfour.player.Player;

public class Piece {
  private final Player player;
  private final Integer rowIndex;
  private final Integer columnIndex;

  /**
   * Build a piece. All fields are required.
   * 
   * @param player
   *          the owner of this piece.
   * @param rowIndex
   *          the row index for this piece, with the bottom row being 0 and the
   *          top numberOfRows - 1
   * @param columnIndex
   *          the column index for this piece, with index 0 being the leftmost
   *          column
   */
  public Piece(Player player, Integer rowIndex, Integer columnIndex) {

    if (null == rowIndex || null == columnIndex || null == player) {
      throw new IllegalArgumentException("Piece values must not be null.");
    }

    if (rowIndex < 0 || columnIndex < 0) {
      throw new IllegalArgumentException(
          "Piece row and column must be non-negative");
    }

    this.player = player;
    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
  }

  /**
   * The row index for this piece in the ConnectFourBoard. The bottom row is 0,
   * and as pieces are stacked on top of each other, they reach the top row with
   * an index of model.getNumberOfRows() - 1.
   * 
   * @return the row index
   */
  public Integer getRowIndex() {
    return rowIndex;
  }

  /**
   * The column index for this piece in the ConnectFourBoard. The leftmost
   * column is index 0, and the rightmost column has an index of
   * model.getNumberOfColumns - 1
   * 
   * @return the column index
   */
  public Integer getColumnIndex() {
    return columnIndex;
  }

  /**
   * The player to whom this piece belongs.
   * 
   * @return the owner of this piece.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * The string representation of this player.
   * 
   * return the string representation of this player
   */
  public String toString() {
    return String.format("%s : [%d, %d]", player.getName(), rowIndex,
        columnIndex);
  }
}
