package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objet.Mur;
import Objet.Pierre;
import Personages.Jouer;


@SuppressWarnings("serial")
public class Donjon extends JPanel {
	//***INITIALISATION***//
	private ImageIcon solFond;
	private Image  sol;
	private int xFond1;
	/*private ImageIcon persoFond;
	private Image  perso;*/
	
	private int persoX;
	private int x;
	private int persoY;
	private int y;
	
	public Jouer joueur;
	public Mur mur;
	public Pierre pierre;

	
	
	//***CONSTRUCTEUR***//
	public Donjon() {
		super();
		this.xFond1 = -50;
		this.persoX = 750;
		this.persoY = 400;
		
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		/*
		persoFond = new ImageIcon("src/images/perso0.png");
		this.perso = this.persoFond.getImage();*/
		
		joueur = new Jouer(this.persoX,this.persoY);
		mur = new Mur(50, 50);
	
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		
		Thread ecranRefresh = new Thread(new SceenRefresh() );
		ecranRefresh.start();
		
		
	}
	
	//***METHODES***//
	public void deplacementPersoX() {
		if(this.persoX!=759 && this.persoX!=50) {
			this.persoX = this.persoX + this.x;
		}
		else if(this.persoX==759) {
			this.persoX = this.persoX -4;
		}
		else if (this.persoX==50){
			this.persoX = this.persoX +3;
		}
	}
	
	public void deplacementPersY() {
		if(this.persoY!=40 && this.persoY!=410) {
			this.persoY = this.persoY + this.y;
		}
		else if(this.persoY==40) {
			this.persoY = this.persoY +4;
		}
		else if(this.persoY==410) {
			this.persoY = this.persoY -4;
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D) g;
		
		this.deplacementPersoX();
		this.deplacementPersY();
		
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.joueur.marche("joueur", 10),this.persoX,this.persoY, null);//posistion du personnage
		g2.drawImage(this.mur.getImgMur(),700,400,null);
		/*for(int x=0; x<= 800;x=x+50) {
			for(int y = 0 ; y<=450;y=y+50) {
				if(x==0||x==800||y==0||y==450) {
				g2.drawImage(this.mur.getImgMur(),x,y,null);
			}	
		}}*/
		
		
	}

	
	
	
	//***GETTER & SETTER***//
	
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
