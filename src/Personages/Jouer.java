package Personages;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Jouer extends Personnage{
	//***INITIALISATION***//
	private Image imgPerso;
	private ImageIcon icoPerso;
	
	
	//**CONSTRUCTEUR***// 
	public Jouer(int x, int y) {
		
	super(x,y,27,27);
	this.icoPerso = new ImageIcon("src/images/perso0.png");
	this.imgPerso = this.icoPerso.getImage();
		
	}


	//***GETTER & SETTER***//
	public Image getImgPerso() {
		return imgPerso;
	}
	

}