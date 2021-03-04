package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Scene extends JPanel {
	
	//INITIALISATION DES VARIBLE
	private ImageIcon sol;
	private Image fondSol;
	private ImageIcon piere;
	private Image fondPiere;
	
	private int xSol;
	
	
	// CONSTRUCTEUR
	public Scene() {
		super();
		
		this.xSol = -50; //comme-ca l'image déborde de chaque coté de notre frame
		
		sol = new ImageIcon("/src/images/sol.jpg");
		this.fondSol = this.sol.getImage();
		piere = new ImageIcon("/src/images/piere.png");
		this.fondPiere=this.piere.getImage();

	}
	
	
	//METHODES
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		g.drawImage(this.fondSol,this.xSol,0,null);
		
	}

}
