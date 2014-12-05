package edu.nyu.mg3626.connectfour.player;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class HumanPlayerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPlayer_withNullName() {
    Player player = new HumanPlayer(null, Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPlayer_withEmptyStringName() {
    Player player = new HumanPlayer("", Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPlayer_withWhitespaceStringName() {
    Player player = new HumanPlayer("   \t \n  ", Color.RED);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructPlayer_withNullColor() {
    Player player = new HumanPlayer("Red", null);
  }

  @Test
  public void testConstuctPlayer_withValidFields() {
    Player player = new HumanPlayer("Red", Color.RED);
    assertEquals(Color.RED, player.getColor());
    assertEquals("Red", player.getName());
  }

  @Test
  public void testToString_equalsPlayerName() {
    Player player = new HumanPlayer("Red", Color.RED);
    assertEquals("Red", player.toString());
  }
}
