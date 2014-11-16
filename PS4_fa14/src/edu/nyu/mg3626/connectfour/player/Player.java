package edu.nyu.mg3626.connectfour.player;

import java.awt.Color;

public abstract class Player {
  private final Color color;
  private final String name;

  public Player(String name, Color color) {
    if (null == name || null == color || name.trim().length() == 0) {
      throw new IllegalArgumentException("Must provide a name and color. ");
    }

    this.name = name;
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public String getName() {
    return name;
  }

  public String toString() {
    return getName();
  }
}
