package Personages;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Personnage {
	//***INITIALISATION***//
	private int largeur , hauteur;//Dimontion du perso
	private int x ,y ;
	private boolean marche;
	private boolean versDroite;
	private boolean versGauche;
	private int compteur;
	
	
	
	//***CONSTRUCTEUR***//
	 public Personnage(int x, int y, int largeur, int hauteur) {
		 setX(x);
		 setY(y);
		 this.largeur=largeur;
		 this.hauteur=hauteur;
		 this.compteur=0;
		 this.marche=false;
		 this.versDroite=false;
		 this.versGauche = false;
		 
	 }
	 
	 //***METHODES***//
	 public Image marche(String nom, int frequence) {
		String str;
		ImageIcon ico;
		Image img;

		if(this.marche == false) {
			if(this.versDroite==true) {
				str ="src/images/perso0droit.png";
			}
			if(this.versGauche==true) {
				str ="src/images/perso0 gauche.png";
			}
			
			else {
				str ="src/images/perso0.png";}}
		
		else {
			this.compteur++;
			if(this.compteur / frequence ==0) {
				if(this.versDroite == true) {
					str ="src/images/perso0droite.png";
				}
				if(this.versGauche==true) {
					str ="src/images/perso0gauche.png";
				}
				else {
					str ="src/images/perso0.png";}}
			
			
			else {
				if(this.versDroite == true) {
					str ="src/images/perso1droite.png";
					if(this.versDroite == true) {
						str ="src/images/perso2droite.png";
					}}
				
				if(this.versGauche==true) {
					str ="src/images/perso1gauche.png";
					if(this.versGauche==true) {
						str ="src/images/perso2gauche.png";
					}
				}
				else {
					str ="src/images/perso1.png";
					if(this.versGauche==false &&this.versDroite == false) {
						str ="src/images/perso2.png";}
					}}
			
			if(this.compteur == 2* frequence) {
				this.compteur=0;
			}
		}
		//affichage du perso
		ico = new ImageIcon(str);
		img = ico.getImage();
		return img;
		
		
	
	
	
	}
		 
		 
	 

	 
	 
	 
	 
	 
	 
	 ///***GETTER & SETTER***//
	public int getLargeur() {
		return largeur;
	}




	public int getHauteur() {
		return hauteur;
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






	public boolean isMarche() {
		return marche;
	}
	public void setMarche(boolean marche) {
		this.marche = marche;
	}






	public boolean isVersDroite() {
		return versDroite;
	}
	public void setVersDroite(boolean versDroite) {
		this.versDroite = versDroite;
	}






	public boolean isVersGauche() {
		return versGauche;
	}

	public void setVersGauche(boolean versGauche) {
		this.versGauche = versGauche;
	}
	
	

	public int getCompteur() {
		return compteur;
	}
	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	 
	 
}
