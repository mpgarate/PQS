package edu.nyu.mg3626.connectfour.player;

import java.awt.Color;

/**
 * A representation of a human player.
 * 
 * @author mg3626
 *
 */
public class HumanPlayer extends Player {

  /**
   * Construct an instance of a HumanPlayer. All fields are required.
   * 
   * @param name
   *          the name of this player
   * @param color
   *          the color of this player
   */
  public HumanPlayer(String name, Color color) {
    super(name, color);
  }
}
