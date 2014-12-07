package edu.mg3626.canvas.model;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.awt.Point;

import org.junit.Test;

import edu.mg3626.canvas.view.CanvasLogger;

public class CanvasModelTest {
  @Test
  public void testFiresEvent() {
    CanvasModel model = new CanvasModel();
    CanvasLogger logger = new CanvasLogger();

    model.addCanvasListener(logger);

    Line line = new Line(new Point(0, 4), new Point(3, 0));
    model.addLine(line);

    assertThat(logger.toString(), containsString("lineAdded((0, 4) -> (3, 0))"));
  }
}
