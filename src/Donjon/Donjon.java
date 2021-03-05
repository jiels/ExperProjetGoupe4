package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Donjon extends JPanel {
	private ImageIcon solFond;
	private Image  sol;
	private ImageIcon piereFond;
	private Image  piere;
	private int xFond1;
	private ImageIcon murFond;
	private Image  mur;
	
	
	
	//CONSTRUCTEUR
	public Donjon() {
		super();
		this.xFond1 = -50;
		
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		piereFond = new ImageIcon("src/images/piere.png");
		this.piere = this.piereFond.getImage();
		murFond = new ImageIcon("src/images/mur.png");
		this.mur = this.murFond.getImage();
		
		
	}
	
	//METHODES
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D) g;
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		
		for(int i = 0; i<=800; i= i+50) {
			for(int j = 0; j<=450; j= j+50) {
				if(j == 400 && i==750) {
					
				}
				else if(i==0||i==800||j==0||j==450) {
					g2.drawImage(mur, i, j, null);
				}
				else {
					g2.drawImage(piere, i, j, null);
				}
				
			}	
		}
		
	}
}
