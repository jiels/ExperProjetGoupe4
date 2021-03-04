package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Donjon extends JPanel {
	private ImageIcon solFond;
	private Image  fond1;
	private ImageIcon piereFond;
	private Image  piere;
	private int xFond1;
	
	private ImageIcon barIfoFond;
	private Image  barIfo;

	
	
	//CONSTRUCTEUR
	public Donjon() {
		super();
		this.xFond1 = -50;
		
		solFond = new ImageIcon("src/images/sol.jpg");
		this.fond1 = this.solFond.getImage();
		piereFond = new ImageIcon("src/images/piere.png");
		this.piere = this.piereFond.getImage();
		barIfoFond = new ImageIcon("src/images/bande_nor.png");
		this.barIfo = this.barIfoFond.getImage();
		
		
	}
	
	//METHODES
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D) g;
		g2.drawImage(this.fond1, this.xFond1, 0, null);//dessin de l'image de fond
		
		
		for(int i = 0; i<=800; i= i+50) {
			for(int j = 0; j<=450; j= j+50) {
				g2.drawImage(piere, i, j, null);
				g2.drawImage(barIfo, 400, 400, null);
			}
			
		}
		
	}
}
