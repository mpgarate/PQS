package edu.mg3626.canvas.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CanvasSwingView {
  private JFrame frame = new JFrame();
  private JPanel panel = new CanvasPanel();

  private Point startPoint;

  public CanvasSwingView() {
    frame.getContentPane().add(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);
    frame.setVisible(true);

    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent event) {
        startPoint = event.getPoint();
      }

      @Override
      public void mouseReleased(MouseEvent event) {
        startPoint = null;
      }
    });

    panel.addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent event) {
        drawLineTo(event.getPoint());
      }

      @Override
      public void mouseMoved(MouseEvent event) {
        drawLineTo(event.getPoint());
      }
    });
  }

  private void drawLineTo(Point point) {
    if (null == startPoint) {
      return;
    }

    Graphics g = panel.getGraphics();

    int x1 = startPoint.x;
    int y1 = startPoint.y;
    int x2 = point.x;
    int y2 = point.y;

    g.drawLine(x1, y1, x2, y2);
    panel.paintComponents(g);

    startPoint = point;
  }
}
