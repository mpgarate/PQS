package edu.mg3626.canvas;

import edu.mg3626.canvas.model.CanvasModel;
import edu.mg3626.canvas.view.CanvasSwingView;

public class CanvasApp {
  void go() {
    CanvasModel model = new CanvasModel();
    CanvasSwingView view = new CanvasSwingView(model);
    CanvasSwingView view2 = new CanvasSwingView(model);
  }

  public static void main(String[] args) {
    new CanvasApp().go();
  }
}
