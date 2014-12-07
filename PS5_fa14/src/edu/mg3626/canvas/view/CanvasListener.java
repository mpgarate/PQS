package edu.mg3626.canvas.view;

import edu.mg3626.canvas.model.Line;

/**
 * The listener interface for the observer pattern. Events defined here are
 * triggered by the CanvasModel class.
 * 
 * @author Michael Garate
 *
 */
public interface CanvasListener {
	/**
	 * Handle event when a line is added to the canvas.
	 * 
	 * @param line
	 *          the newly added line
	 */
	void lineAdded(Line line);
}
