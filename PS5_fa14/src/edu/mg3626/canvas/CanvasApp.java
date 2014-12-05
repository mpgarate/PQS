package edu.mg3626.canvas;

import edu.mg3626.canvas.view.CanvasSwingView;

public class CanvasApp {
  void go() {
    CanvasSwingView view = new CanvasSwingView();
  }

  public static void main(String[] args) {
    new CanvasApp().go();
  }
}
