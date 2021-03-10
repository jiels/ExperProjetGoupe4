package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class PotionVie extends Objet {
	//***INITIALISATION ***//
	private Image imgpotion;
	private ImageIcon  icopotion;

	
	
	//***CONSTRUCTEUR***//
	public PotionVie(int x, int y) {
		super(x,y,39,38);
		setX(x);
		setY(y);
		this.icopotion = new ImageIcon("src/images/potion.png");
		this.imgpotion = this.icopotion.getImage();
	}
	
	//***METHODES***//
	
	
	
	//***GATTER&SETTER***//
	public Image getImgpotion() {
		return imgpotion;
	}




}
