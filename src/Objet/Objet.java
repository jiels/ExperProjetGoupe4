package Objet;

public class Objet implements Comparable<Objet> {
	//***INITIALISATION***//
	private int largeur , hauteur;//Dimontion de l'objet
	private int x ,y ;
	
	//***CONSTRUCTEUR***//
	public Objet(int x, int y, int largeur, int hauteur) {
		setX(x);
		setY(y);
		setLargeur(largeur);
		setHauteur(hauteur);
		
	}
	
	//***METHODES***//
	
	
	//***SETTER & GETTTER*///

	public int getLargeur() {
		return largeur;
	}
	public void setLargeur(int largeur) {
		this.largeur = largeur;}
	
	

	public int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;}

	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;}

	
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;}

	
	
	public int compareTo(Objet o) {
		
		if (this.x==o.x&&this.y==o.y) {
			return 0;
		}

        return 1;
	}
	
	
	
	
	
	

}
