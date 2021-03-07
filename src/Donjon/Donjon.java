package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objet.Mur;
import Objet.Objet;
import Objet.Pierre;
import Personages.Jouer;


@SuppressWarnings("serial")
public class Donjon extends JPanel {
	//***INITIALISATION***//
	private ImageIcon solFond;
	private Image  sol;
	private int xFond1;		
	
	private int persoX;
	private int x;
	private int persoY;
	private int y;
	
	public Jouer joueur;
	//initialisation objet
	public Mur mur;
	public Mur mur2;
	public Pierre pierre;

	
	
	//***CONSTRUCTEUR***//
	public Donjon() {
		super();
		this.xFond1 = -50;
		this.persoX = 750;
		this.persoY = 400;
				
		
		//Fond
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		
		//Joueur
		joueur = new Jouer(this.persoX,this.persoY);
		
		//Murs
		mur = new Mur(700,350);
		mur2 = new Mur(700,300);
		
	
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		
		Thread ecranRefresh = new Thread(new SceenRefresh() );
		ecranRefresh.start();}
	
	
	
	
	//***METHODES***//
	public void deplacementPersoX() {
		if(this.persoX==769) {
			this.persoX = this.persoX -4;
		}
		else if (this.persoX==35){
			this.persoX = this.persoX +3;
		}
		else{
			this.persoX = this.persoX + this.x;
		}
	}
	
	public void deplacementPersY() {
		if(this.persoY==30) {
			this.persoY = this.persoY +4;
		}
		else if(this.persoY==425) {
			this.persoY = this.persoY -4;
		}
		else {
			this.persoY = this.persoY + this.y;
		}
	}
	
	public void solide(Objet odjet) {
		
		if(joueur.collision(odjet)) {
			if(joueur.isVersDroite()) {
				this.persoX=this.persoX-1;
			}
			if(joueur.isVersGauche()) {
				this.persoX=this.persoX+1;
			}
			if(joueur.isVersH()) {
				this.persoY=this.persoY+1;
			}
			if(joueur.isVersB()) {
				this.persoY=this.persoY-1;
			}
		}
	}
		

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		
		//Collision solide
		solide(mur);
		solide(mur2);


		
		//Déplacement  personnage
		this.deplacementPersoX();
		this.deplacementPersY();
		joueur.setX(persoX);
		joueur.setY(persoY);
		
		
		
		
		
		
		
		//placement du perso et du fond
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.joueur.marche("joueur", 5),joueur.getX(),joueur.getY(), null);//posistion du personnage
		
		//placement des mur dans la zone d'action du persopnnage
		g2.drawImage(this.mur.getImgMur(),700,350,null);
		g2.drawImage(this.mur2.getImgMur(),700,300,null);
		
		//placement des murs de délimitation de la zone d'action du persopnnage
		for(int x=0; x<= 800;x=x+50) {
			for(int y = 0 ; y<=450;y=y+50) {
				if(x==0||x==800||y==0||y==450) {
				g2.drawImage(this.mur.getImgMur(),x,y,null);
			}	
		}}
		
		
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
