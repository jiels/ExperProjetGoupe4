package Personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Joueur extends Personnage{
	//***INITIALISATION***//
	private Image imgPerso;
	private ImageIcon icoPerso;
	
	
	//**CONSTRUCTEUR***// 
	public Joueur(int x, int y) {
		
	super(x,y,27,27);
	this.icoPerso = new ImageIcon("src/images/perso0.png");
	this.imgPerso = this.icoPerso.getImage();
		
	}
	public void machX(int x) {
		int X = getX()+x;
		setX(X);
		
		
	}
	public void machY(int y) {
		int Y = getY()+y;
		setX(Y);
		
		
	}


	//***GETTER & SETTER***//
	public Image getImgPerso() {
		return imgPerso;
	}
	

}