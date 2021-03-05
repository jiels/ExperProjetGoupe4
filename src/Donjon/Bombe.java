package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bombe {
	private Image BombeImage;
	private int nbBombe;
	private final int id;
	private static int compteur;
	
	//private ImageIcon BombeFond; peut-être ?

	//***CONSTRUCTEUR***//
	public Bombe() {
		id=compteur++;
	}
	public Bombe(int nbBombe){ // j'aimerais initialiser plusieurs bombes directement
		id=compteur++;
		int i=1;
		while(i<nbBombe){
	    Bombe bi = new Bombe();
	    i++;
		}
		
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Bombe a= new Bombe(5);
System.out.println(a);
	}
	

	//***METHODES***//
	
	
	
	
	
	//***GETTER & SETTER***//
	
	public Image getBombeImage() {
		return BombeImage;
	}

	public void setBombeImage(Image bombeImage) {
		BombeImage = bombeImage;
	}

	public int getNbBombe() {
		return nbBombe;
	}

	public void setNbBombe(int nbBombe) {
		this.nbBombe = nbBombe;
	}
}
