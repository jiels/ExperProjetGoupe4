package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;


public class Bombe extends Objet {

	//***INITIALISATION ***//
	private Image imgBombe;
	private ImageIcon  icoBombe;

	//***CONSTRUCTEUR***//
	public Bombe(int x, int y) {
		super(x,y,39,38);
		setX(x);
		setY(y);
		this.icoBombe = new ImageIcon("src/images/Bombe/000.png");
		this.imgBombe = this.icoBombe.getImage();
	}
	
	//***METHODES***//


	
	
	//***GATTER&SETTER***//


	public Image getImgBombe() {
		return imgBombe;
	}


	public void setImgBombe(Image explosion) {
		this.imgBombe =  explosion;
		
	}
	
}
