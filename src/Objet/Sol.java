package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Sol extends Objet {

	//***INITIALISATION ***//
	private Image imgSol;
	private ImageIcon  icoSol;
 
	
	//***CONSTRUCTEUR***//
	public Sol(int x, int y) {
		super(x,y,39,38);
		setX(x);
		setY(y);
		this.icoSol = new ImageIcon("src/images/sol.jpg");
		this.imgSol = this.icoSol.getImage();
	}
	
	//***METHODES***//


	
	
	//***GATTER&SETTER***//


	public Image getImgSol() {
		return imgSol;
	}



	
}