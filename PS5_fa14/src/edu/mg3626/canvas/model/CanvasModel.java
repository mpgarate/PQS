package edu.mg3626.canvas.model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import edu.mg3626.canvas.CanvasListener;

public class CanvasModel {

  private Set<Line> lines = new LinkedHashSet<Line>();
  private List<CanvasListener> listeners = new ArrayList<CanvasListener>();

  public CanvasModel() {

  }

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
