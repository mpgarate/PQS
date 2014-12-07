package edu.mg3626.canvas.model;

import java.awt.Point;

public class Line {
  private final int x1;
  private final int y1;
  private final int x2;
  private final int y2;

  public Line(Point p1, Point p2) {
    this.x1 = p1.x > 0 ? p1.x : 0;
    this.y1 = p1.y > 0 ? p1.y : 0;
    this.x2 = p2.x > 0 ? p2.x : 0;
    this.y2 = p2.y > 0 ? p2.y : 0;
  }

  public int getX1() {
    return x1;
  }

  public int getY1() {
    return y1;
  }

  public int getX2() {
    return x2;
  }

  public int getY2() {
    return y2;
  }

  public String toString() {
    return String.format("(%d, %d) -> (%d, %d)", x1, y1, x2, y2);
  }
}
