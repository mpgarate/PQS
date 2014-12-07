package edu.mg3626.canvas.model;

import java.util.ArrayList;
import java.util.List;

import edu.mg3626.canvas.CanvasListener;

public class CanvasModel {

  private List<CanvasListener> listeners = new ArrayList<CanvasListener>();
  private List<Line> lines = new ArrayList<Line>();

  public void addCanvasListener(CanvasListener listener) {
    listeners.add(listener);
  }

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
