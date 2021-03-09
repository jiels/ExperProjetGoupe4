package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Mur extends Objet {
	
	//***INITIALISATION ***//
	private Image imgMur;
	private ImageIcon  icoMur;
	private int x;
	private int y;
	
	
	//***CONSTRUCTEUR***//
	public Mur(int x, int y) {
		super(x,y,38,38);
		setX(x);
		setY(y);
		this.icoMur = new ImageIcon("src/images/mur.png");
		this.imgMur = this.icoMur.getImage();
	}
	
	//***METHODES***//
	
	
	
	//***GATTER&SETTER***//
	public Image getImgMur() {
		return imgMur;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	

}
