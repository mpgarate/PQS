package edu.mg3626.canvas.model;

import java.awt.Point;

/**
 * Representation of a line between two points with nonnegative coordinates.
 * 
 * @author Michael Garate
 *
 */
public class Line {
	private final int x1;
	private final int y1;
	private final int x2;
	private final int y2;

	/**
	 * Construct a line from two points. If any coordinate of the points is less
	 * than 0, a value of 0 will be used in its place.
	 * 
	 * @param p1
	 * @param p2
	 */
	public Line(Point p1, Point p2) {
		this.x1 = p1.x > 0 ? p1.x : 0;
		this.y1 = p1.y > 0 ? p1.y : 0;
		this.x2 = p2.x > 0 ? p2.x : 0;
		this.y2 = p2.y > 0 ? p2.y : 0;
	}

	/**
	 * Get the x coordinate of the first point in the line.
	 * 
	 * @return the x coordinate
	 */
	public int getX1() {
		return x1;
	}

	/**
	 * Get the y coordinate of the first point in the line.
	 * 
	 * @return the y coordinate
	 */
	public int getY1() {
		return y1;
	}

	/**
	 * Get the x coordinate of the second point in the line.
	 * 
	 * @return the x coordinate
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * Get the y coordinate of the second point in the line.
	 * 
	 * @return the y coordinate
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * The String representation of this line.
	 */
	public String toString() {
		return String.format("(%d, %d) -> (%d, %d)", x1, y1, x2, y2);
	}
}
