package edu.mg3626.canvas;

import edu.mg3626.canvas.model.CanvasModel;
import edu.mg3626.canvas.view.CanvasSwingView;

/**
 * Run the Canvas application using an instance of CanvasModel and at least one
 * CanvasSwingView.
 * 
 * @author Michael Garate
 *
 */
public class CanvasApp {
	void go() {
		CanvasModel model = new CanvasModel();

		new CanvasSwingView(model);
		new CanvasSwingView(model);
	}

	public static void main(String[] args) {
		new CanvasApp().go();
	}
}
