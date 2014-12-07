package edu.mg3626.canvas.model;

import java.util.ArrayList;
import java.util.List;

import edu.mg3626.canvas.view.CanvasListener;

/**
 * Representation of a canvas. Markings are recorded as lines between two points
 * with non-negative coordinates.
 * 
 * @author Michael Garate
 *
 */
public class CanvasModel {

	private List<CanvasListener> listeners = new ArrayList<CanvasListener>();
	private List<Line> lines = new ArrayList<Line>();

	/**
	 * Add a listener to this model to receive events when the model is updated.
	 * 
	 * @param listener
	 *          the listener to add
	 */
	public void addCanvasListener(CanvasListener listener) {
		listeners.add(listener);
	}

	/**
	 * Add a line to this canvas.
	 * 
	 * @param line
	 *          the line to add.
	 */
	public void addLine(Line line) {
		lines.add(line);
		fireLineAddedEvent(line);
	}

	private void fireLineAddedEvent(Line line) {
		for (CanvasListener listener : listeners) {
			listener.lineAdded(line);
		}
	}

}
