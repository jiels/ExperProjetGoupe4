package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Cle extends Objet {

	//***INITIALISATION ***//
	private Image imgCle;
	private ImageIcon  icoCle;
 
	
	//***CONSTRUCTEUR***//
	public Cle(int x, int y) {
		super(x,y,39,38);
		setX(x);
		setY(y);
		this.icoCle = new ImageIcon("src/images/clé.png");
		this.imgCle = this.icoCle.getImage();
	}
	
	//***METHODES***//


	
	
	//***GATTER&SETTER***//


	public Image getImgCle() {
		return imgCle;
	}



	
}