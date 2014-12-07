package edu.mg3626.canvas.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.mg3626.canvas.model.CanvasModel;
import edu.mg3626.canvas.model.Line;

/**
 * The GUI view for the canvas.
 * 
 * @author Michael Garate
 *
 */
public class CanvasSwingView implements CanvasListener {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private final CanvasModel model;

	private Point startPoint;

	/**
	 * Construct a CanvasSwingView
	 * 
	 * @param model
	 *          the canvas model
	 */
	public CanvasSwingView(CanvasModel model) {
		this.model = model;
		model.addCanvasListener(this);

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

		model.addLine(new Line(startPoint, point));

		startPoint = point;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void lineAdded(Line line) {
		Graphics g = panel.getGraphics();

		int x1 = line.getX1();
		int y1 = line.getY1();
		int x2 = line.getX2();
		int y2 = line.getY2();

		g.drawLine(x1, y1, x2, y2);
		panel.paintComponents(g);
	}
}
