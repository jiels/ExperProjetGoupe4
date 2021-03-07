package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Mur extends Objet {
	
	//***INITIALISATION ***//
	private Image imgMur;
	private ImageIcon  icoMur;
	
	
	//***CONSTRUCTEUR***//
	public Mur(int x, int y) {
		super(x,y,50,50);
		this.icoMur = new ImageIcon("src/images/mur.png");
		this.imgMur = this.icoMur.getImage();
	}
	
	//***METHODES***//
	
	
	
	//***GATTER&SETTER***//
	public Image getImgMur() {
		return imgMur;
	}
	

}
