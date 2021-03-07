package Objet;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Pierre extends Objet {

	//***INITIALISATION ***//
		private Image imgPiere;
		private ImageIcon icoPiere;
		
		
		//***CONSTRUCTEUR***//
		public Pierre(int x, int y) {
			super(x,y,50,50);
			this.icoPiere = new ImageIcon("src/images/mur.png");
			this.imgPiere = this.icoPiere.getImage();
		}
		
		//***METHODES***//
		
		
		
		//***GATTER&SETTER***//
		public Image getImgMur() {
			return imgPiere;
		}
}
