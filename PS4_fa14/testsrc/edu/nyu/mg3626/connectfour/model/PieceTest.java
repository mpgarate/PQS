package edu.nyu.mg3626.connectfour.model;

import static edu.nyu.mg3626.connectfour.util.TestUtil.RED_PLAYER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PieceTest {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructPiece_withNullPlayer() {
    Piece piece = new Piece(null, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPiece_withNullRow() {
    Piece piece = new Piece(RED_PLAYER, null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPiece_withNullColumn() {
    Piece piece = new Piece(RED_PLAYER, 0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPiece_withNegativeColumn() {
    Piece piece = new Piece(RED_PLAYER, 0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPiece_withNegativeRow() {
    Piece piece = new Piece(RED_PLAYER, -1, 0);
  }

  @Test
  public void testConstructPiece() {
    Piece piece = new Piece(RED_PLAYER, 5, 0);
    assertEquals(RED_PLAYER, piece.getPlayer());
    assertEquals(new Integer(5), piece.getRowIndex());
    assertEquals(new Integer(0), piece.getColumnIndex());
  }

  @Test
  public void testToString() {
    Piece piece = new Piece(RED_PLAYER, 5, 0);
    String pieceString = piece.toString();

    assertTrue(pieceString.contains("5"));
    assertTrue(pieceString.contains("0"));
    assertTrue(pieceString.contains("Red"));
  }
}
