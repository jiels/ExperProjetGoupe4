package Personnages;

import java.awt.Image;

import javax.swing.ImageIcon;

import Objet.Bombe;
import Objet.Objet;

public class Personnage {
	//***INITIALISATION***//
	private int largeur , hauteur;//Dimontion du perso
	private int x ,y ;
	private boolean marche;
	private boolean versDroite;
	private boolean versGauche;
	private boolean versH;
	private boolean versB;
	private int compteur;
	private int vie;
	private int potion;
	
	
	
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
		 this.versH=false;
		 this.versB=false;
		 setVie(5);
		 this.potion=0;
		 
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
		 
		 
	 public boolean collision(Objet r) {
		 /*
		  * j'ai trouver ce code dans le tuto d'un youtubeur 
		  * nommé "BroCode"
		  * voici le lien vers la vidéo
		  * https://www.youtube.com/watch?v=qIr2XYZrznI
		  */
		 boolean a = false;
		 int rw = r.getHauteur();
		 int rh = r.getLargeur();
		 int jw = this.largeur;
		 int jh = this.hauteur;
		 /////////////////////
		 int jx = this.x;
		 int jy = this.y;
		 int rx = r.getX();
		 int ry = r.getY();
		 /////////////////////
		 rw+=rx;
		 rh+=ry;
		 jw+=jx;
		 jh+=jy;
		if(this.isVersDroite() == true ||this.isVersGauche()==true || (this.isVersH() == true||this.isVersB()==true) ){
			if((rw < rx || rw> jx)&&(rh < ry || rh >jy)&&(jw < jx || jw >rx)&&(jh < jy || jh > ry)) {
				a = true;
			}
		}
		else {a= false;}
		return a;
	}
 
	 
	 public void ramaserPotion(Objet odjet) {
		 if(this.collision(odjet)) {
			 odjet.setX(-50);
			 odjet.setY(-50);
			 setPotion(1);
		 }
	 }
	 
	 public void usePotion() {
		 if(getVie()!=5&&getPotion()!=0) {
			 setPotion(-1);
		 }
	 }
	 
	  
	 public void BombeEx(Bombe bombe) {
		 
		 
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

	
	public boolean isVersH() {
		return versH;
	}

	public void setVersH(boolean versH) {
		this.versH = versH;
	}

	
	public boolean isVersB() {
		return versB;
	}

	public void setVersB(boolean versB) {
		this.versB = versB;
	}

	
	
	public int getVie() {
		return vie;
	}

	
	public void setVie(int vie) {
		if(this.vie<5) {
			this.vie += vie;
		}
	}

	
	
	public int getPotion() {
		return potion;
	}

	public void setPotion(int potion) {
		this.potion += potion;
	}
	
	
	
	

	 
	 
}
