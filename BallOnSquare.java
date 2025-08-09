package events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BallOnSquare extends JPanel{
    private int ballX, ballY;

    private static final int DIAMETER = 30;
    private static final int SPEED = 3;
    private static final int PANEL_SIZE = 500;
    private static final int DELAY = 10;

    // Square bounds
    private static final int MARGIN = 100;

    // Current edge: 0=top, 1=right, 2=bottom, 3=left
    private int edge = 0;

    // Direction: true = clockwise, false = anticlockwise
    private boolean clockwise = true;
   
    private JPanel drawingPanel;
    public BallOnSquare() {
    	setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
    	setLayout(new BorderLayout());
    	
    	ballX = MARGIN ;
        ballY = MARGIN;
    	
        setFocusable(true);
		requestFocusInWindow();
		
    	RepaintActionListener actionListener = new RepaintActionListener();
    	
    	addKeyListener(new RepaintKeyListener());
    	
    	Timer timer = new Timer(DELAY, actionListener);  
    	timer.start();
    	
    	JLabel label = new JLabel("Ball on a Square");
    	
    	drawingPanel = new DrawingPanel();
    	
    	drawingPanel.add(label,BorderLayout.NORTH);
    	drawingPanel.setBackground(Color.GREEN);
    	
    	add(drawingPanel);
    }
    
    private class DrawingPanel extends JPanel{
    	@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawSquare(g);
			drawBall(g);
		}
    }
    
    private class RepaintKeyListener extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_A) {
				clockwise = false;
				repaint();
			}  if (e.getKeyCode() == KeyEvent.VK_C) {
				clockwise = true;			
			} 
		}
    }
    
    private class RepaintActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (clockwise) {
                switch (edge) {
                    case 0: //  right
                        ballX += SPEED;
                        if (ballX >= PANEL_SIZE - MARGIN ) {
                            ballX = PANEL_SIZE - MARGIN ;
                            edge = 1;
                        }
                        break;
                    case 1: //  down
                        ballY += SPEED;
                        if (ballY >= PANEL_SIZE - MARGIN ) {
                            ballY = PANEL_SIZE - MARGIN ;
                            edge = 2;
                        }
                        break;
                    case 2: //  left
                        ballX -= SPEED;
                        if (ballX <= MARGIN) {
                            ballX = MARGIN;
                            edge = 3;
                        }
                        break;
                    case 3: // up
                        ballY -= SPEED;
                        if (ballY <= MARGIN) {
                            ballY = MARGIN;
                            edge = 0;
                        }
                        break;
                }
            } else { // Anti-clockwise
                switch (edge) {
                    case 0: // left
                        ballX -= SPEED;
                        if (ballX <= MARGIN) {
                            ballX = MARGIN;
                            edge = 3;
                        }
                        break;
                    case 3: // down
                        ballY += SPEED;
                        if (ballY >= PANEL_SIZE - MARGIN) {
                            ballY = PANEL_SIZE - MARGIN ;
                            edge = 2;
                        }
                        break;
                    case 2: //  right
                        ballX += SPEED;
                        if (ballX >= PANEL_SIZE - MARGIN ) {
                            ballX = PANEL_SIZE - MARGIN ;
                            edge = 1;
                        }
                        break;
                    case 1: //  up
                        ballY -= SPEED;
                        if (ballY <= MARGIN) {
                            ballY = MARGIN;
                            edge = 0;
                        }
                        break;
                }
            }
            repaint();
        }
    }

    
    private void drawSquare(Graphics g) {
    	g.setColor(Color.BLACK);
    	int size = PANEL_SIZE - 2 * MARGIN;
    	g.drawRect(MARGIN, MARGIN, size, size);
    
    }
    private void drawBall(Graphics g) {
    	g.setColor(Color.RED);
    	
    	g.fillOval(ballX - DIAMETER/2, ballY - DIAMETER/2, DIAMETER, DIAMETER);
    }
    
 

    public static void main(String[] args) {
        JFrame frame = new JFrame("Square Path Ball");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BallOnSquare());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
