package edu.mg3626.canvas.view;

import edu.mg3626.canvas.CanvasListener;
import edu.mg3626.canvas.model.Line;

public class CanvasLogger implements CanvasListener {

  StringBuilder sb = new StringBuilder();

  @Override
  public void lineAdded(Line line) {
    sb.append("lineAdded(" + line + ")");
  }

  public String toString() {
    return sb.toString();
  }

}
