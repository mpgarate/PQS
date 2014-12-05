package edu.mg3626.canvas.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CanvasPanel extends JPanel {

  private Point startPoint;

  public CanvasPanel() {

  }

  @Override
  public void mouseClicked(MouseEvent event) {
    startPoint = event.getPoint();
  }

  @Override
  public void mouseDragged(MouseEvent event) {
    if (null == startPoint) {
      return;
    }

    Point endPoint = event.getPoint();

    Graphics g = getGraphics();

    int x1 = startPoint.x;
    int y1 = startPoint.y;
    int x2 = endPoint.x;
    int y2 = endPoint.y;

    g.drawLine(x1, y1, x2, y2);
    paintComponents(g);
    repaint();
  }

}
