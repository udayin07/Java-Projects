package pracseven;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class Plant extends JPanel {
	    private int stage = 0; // 0 = seed, 1 = sprout, 2 = full plant
	    private final Random rand = new Random();
		private static final int PANEL_WIDTH = 400;
		private static final int PANEL_HEIGHT = 400;
		
		private static final int SOIL_WIDTH = 200;
		private static final int SOIL_HEIGHT = 50;
		private static final int SEED_DIAM = 20;
		private static final int SPROUT_DIAM = 40;
		private static final int SPROUT_HEIGHT = 40;
		private static final int STEM_WIDTH = 4;
		
	    private String plantStage;
	    private JPanel plantPanel;

	   
	public Plant(){
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(new BorderLayout(1,2));
		
		stage = rand.nextInt(3);
		updateStageName();
	
		plantPanel = new PlantPanel();
		add(plantPanel, BorderLayout.CENTER);
		
		//add Button
		JButton btnGrow = new JButton("Grow");
		btnGrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {	
				//call repaint method
				growPlant();
			}
		});
		plantPanel.add(btnGrow);
		add(btnGrow, BorderLayout.SOUTH);
		
		
	}
	
	private void updateStageName() {
        switch (stage) {
            case 0 -> plantStage = "Seed";
            case 1 -> plantStage = "Sprout";
            case 2 -> plantStage = "Full Plant";
        }
    }
	
	public void growPlant() {
		stage = rand.nextInt(3);
		updateStageName();
		repaint();
		
	}
	
	private class PlantPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawPlant(g);
		}
	}
	
	private void drawPlant(Graphics g) {
		
		int panelWidth = plantPanel.getWidth();
        int panelHeight = plantPanel.getHeight();
        
     // Soil
        int soilX = (panelWidth - SOIL_WIDTH) / 2;
        int soilY = (panelHeight - SOIL_HEIGHT) / 2;
		
		//draw soil
		g.setColor(new Color(139, 69, 19)); 
		g.fillRect(soilX, soilY, SOIL_WIDTH, SOIL_HEIGHT);
		
		//Set stage font to bold
		Font boldFont = new Font("SansSerif", Font.BOLD, 14); 
		g.setFont(boldFont);
		int labelX = SOIL_WIDTH - SOIL_HEIGHT/2;
		int labelY = SOIL_WIDTH - 10;
		
		int centerX = panelWidth / 2;
		switch (stage) {
		case 0:
			g.setColor(Color.BLACK);
			int seedX = centerX - SEED_DIAM / 2;
            int seedY = soilY - SEED_DIAM;
            
			g.drawString(plantStage, labelX, labelY);
			
			g.fillOval(seedX, seedY, SEED_DIAM, SEED_DIAM);
			break;
		case 1 :
			g.setColor(Color.GREEN);
			int stemX = centerX - STEM_WIDTH / 2;
            int stemY = soilY - SPROUT_HEIGHT;
            
			g.drawString(plantStage, labelX, labelY);
			g.fillRect(stemX, stemY, STEM_WIDTH, SPROUT_HEIGHT);
			
			int leafX = centerX - SPROUT_DIAM / 2;
            int leafY = stemY - SPROUT_DIAM;
			g.fillOval(leafX, leafY, SPROUT_DIAM, SPROUT_DIAM);
			break;
		case 2 : 
			g.setColor(Color.GREEN);
			g.drawString(plantStage, labelX, labelY);
			int fullStemHeight = SPROUT_HEIGHT * 2;
             stemX = centerX - STEM_WIDTH / 2;
             stemY = soilY - fullStemHeight;
            g.fillRect(stemX, stemY, STEM_WIDTH, fullStemHeight);

            int leftLeafX = centerX - SPROUT_HEIGHT *2;
            int leftLeafY = stemY - SPROUT_DIAM * 2 + SPROUT_HEIGHT;
            
           int rightLeafX = centerX ;
            int rightLeafY = stemY - SPROUT_DIAM * 2 + SPROUT_HEIGHT; 

            
            g.fillOval(leftLeafX, leftLeafY, SPROUT_DIAM*2, SPROUT_DIAM*2);
            g.fillOval(rightLeafX, rightLeafY, SPROUT_DIAM*2, SPROUT_DIAM*2);
			break;
		default:
			plantStage = null;
			break;
		}
	}
	
}
