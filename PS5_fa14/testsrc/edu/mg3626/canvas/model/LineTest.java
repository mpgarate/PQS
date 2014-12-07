package edu.mg3626.canvas.model;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

public class LineTest {
  @Test
  public void testConstructor_throwsExceptionForNegativeX1() {
    Line line = new Line(new Point(-1, 0), new Point(0, 3));

    assertEquals("(0, 0) -> (0, 3)", line.toString());
  }
}
