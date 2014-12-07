package edu.mg3626.canvas.model;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Test;

public class LineTest {
	@Test
	public void testToString() {
		Line line = new Line(new Point(555, 0), new Point(1579, 3));

		assertEquals("(555, 0) -> (1579, 3)", line.toString());
	}

	@Test
	public void testConstructor_setsNegativeX1ToZero() {
		Line line = new Line(new Point(-1, 0), new Point(0, 3));

		assertEquals("(0, 0) -> (0, 3)", line.toString());
	}

	@Test
	public void testConstructor_setsNegativeY1ToZero() {
		Line line = new Line(new Point(3, -1), new Point(0, 3));

		assertEquals("(3, 0) -> (0, 3)", line.toString());
	}

	@Test
	public void testConstructor_setsNegativeX2ToZero() {
		Line line = new Line(new Point(3, 4), new Point(-4, 3));

		assertEquals("(3, 4) -> (0, 3)", line.toString());
	}

	@Test
	public void testConstructor_setsNegativeY2ToZero() {
		Line line = new Line(new Point(6, 7), new Point(1, -99999));

		assertEquals("(6, 7) -> (1, 0)", line.toString());
	}

	@Test
	public void testGetX1() {
		Line line = new Line(new Point(5, 4), new Point(2, 1));

		assertEquals(5, line.getX1());
	}

	@Test
	public void testGetY1() {
		Line line = new Line(new Point(5, 4), new Point(2, 1));

		assertEquals(4, line.getY1());
	}

	@Test
	public void testGetX2() {
		Line line = new Line(new Point(5, 4), new Point(2, 1));

		assertEquals(2, line.getX2());
	}

	@Test
	public void testGetY2() {
		Line line = new Line(new Point(5, 4), new Point(2, 1));

		assertEquals(1, line.getY2());
	}
}
