package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Coeur extends Objet {
	//***INITIALISATION ***//
	private Image imgCr;
	private ImageIcon  icoCr;

	
	
	//***CONSTRUCTEUR***//
	public Coeur(int x, int y) {
		super(x,y,39,38);
		setX(x);
		setY(y);
		this.icoCr = new ImageIcon("src/images/Coeur.png");
		this.imgCr = this.icoCr.getImage();
	}
	
	//***METHODES***//
	
	
	
	//***GATTER&SETTER***//
	public Image getImgCr() {
		return imgCr;
	}




}
