package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Bombe extends Objet {

		//***INITIALISATION ***//
	private Image imgBombe;
	private ImageIcon  icoBombe;

		//***CONSTRUCTEUR***//
	public Bombe(int x, int y) {
		super(x,y,50,50);
		this.icoBombe = new ImageIcon("src/images/bb1.gif");
		this.imgBombe = this.icoBombe.getImage();
	}
	

		//***METHODES***//
		
		
		
		//***GATTER&SETTER***//
	public Image getImgBombe() {
		return imgBombe;
	}
		
		

	

}


