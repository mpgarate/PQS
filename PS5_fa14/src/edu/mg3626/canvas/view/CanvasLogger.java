package edu.mg3626.canvas.view;

import edu.mg3626.canvas.model.Line;

/**
 * Log events received as a CanvasListener.
 * 
 * @author Michael Garate
 *
 */
public class CanvasLogger implements CanvasListener {

	StringBuilder sb = new StringBuilder();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void lineAdded(Line line) {
		sb.append("lineAdded(" + line + ")");
	}

	/**
	 * Get the contents of this log.
	 * 
	 * @return the log contents
	 */
	public String toString() {
		return sb.toString();
	}

}
