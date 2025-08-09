package pracseven;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Target extends JPanel {
	private static final int SMALLEST_DIAM = 50;
	private static final int GAP = 50;
	private static final int PANEL_WIDTH = 400;
	private static final int PANEL_HEIGHT = 400;

	private int numCircles = 5;
	private JPanel drawingPanel;
	private JPanel buttonPanel;

	public Target() {
		setPreferredSize(new Dimension(PANEL_WIDTH + 200, PANEL_HEIGHT + 200));
		setLayout(new BorderLayout());
		drawingPanel = new DrawingPanel();
		
		add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.setPreferredSize(new Dimension(400,400));
		
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(2,1, 10, 10));
		buttonPanel.setPreferredSize(new Dimension(300, 400));
		
		//Remove circle
		JButton btnRemove = new JButton("Remove Circle");
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCircle();	
			}
		});
		//Add circle
		
		JButton btnAdd = new JButton("Add Circle");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addCircle();	
			}
		});
		
		buttonPanel.add(btnRemove);
		buttonPanel.add(btnAdd);
		add(buttonPanel, BorderLayout.EAST);
		
	}
	public void addCircle() {
		if (numCircles < 6) {
		numCircles++;
		repaint();
		}
	}
	
	public void removeCircle() {
		if (numCircles > 1) {
			numCircles--;
			repaint();
		}
	}
	
	private class DrawingPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawTarget(g, getWidth() / 2, getHeight() / 2);
			drawDiagLines(g);
		}
	}
	
	private void drawTarget(Graphics g, int centerX, int centerY) {

	    int step = SMALLEST_DIAM;

	    for (int i = numCircles - 1; i >= 0; i--) {
	        int diam = SMALLEST_DIAM + i * step;
	        int topLeftX = centerX - diam / 2;
	        int topLeftY = centerY - diam / 2;

	        if (i % 2 == 0) {
	            g.setColor(Color.red);
	        } else {
	            g.setColor(Color.black);
	        }

	        g.fillOval(topLeftX, topLeftY, diam, diam);
	    }
	}
	
	private void drawDiagLines(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		g2.setColor(Color.blue);
		
		int width = drawingPanel.getWidth();
	    int height = drawingPanel.getHeight();
	    
		 // Diagonal from top-left to bottom-right
	    g2.drawLine(GAP, GAP, width - GAP, height - GAP);

	    // Diagonal from top-right to bottom-left
	    g2.drawLine(width - GAP, GAP, GAP, height - GAP);

	}
	
	
}
