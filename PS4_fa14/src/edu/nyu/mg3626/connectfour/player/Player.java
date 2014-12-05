package edu.nyu.mg3626.connectfour.player;

import java.awt.Color;

/**
 * Abstract representation of a player of the connect four game.
 * 
 * @author mg3626
 *
 */
public abstract class Player {
  private final Color color;
  private final String name;

  /**
   * Construct a player. All fields are required and must be non-null. The name
   * must contain non-whitespace characters.
   * 
   * @param name
   *          the player name
   * @param color
   *          the player color
   */
  public Player(String name, Color color) {
    if (null == name || null == color || name.trim().length() == 0) {
      throw new IllegalArgumentException("Must provide a name and color. ");
    }

    this.name = name;
    this.color = color;
  }

  /**
   * Get the color of this player
   * 
   * @return the color
   */
  public Color getColor() {
    return color;
  }

  /**
   * Get the name of this player
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Get a string representation of this player.
   */
  public String toString() {
    return getName();
  }
}
