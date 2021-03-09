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
	public Mur mur3;
	public Mur mur4;
	public Mur mur5;
	public Mur mur6;
	public Mur mur7;
	public Mur mur8;
	public Mur mur9;
	public Mur mur10;
	public Mur mur11;
	public Mur mur12;
	public Mur mur13;
	public Mur mur14;
	public Mur mur15;
	public Mur mur16;
	public Mur mur17;
	public Mur mur18;
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
		
		//Plmacement Murs
		mur = new Mur(700,350);
		mur2 = new Mur(700,300);
		mur3 = new Mur(100,50);
		mur4 = new Mur(100,100);
		mur5 = new Mur(300,300); 
		mur6 = new Mur(350,250);
		mur7 = new Mur(750,200);
		mur8 = new Mur(500,200);
		mur9 = new Mur(600,300);
		mur10 = new Mur(600,400);
		mur11 = new Mur(150,300);
		mur12 = new Mur(400,150);
		mur13 = new Mur(150,150);
		mur14 = new Mur(650,150);
		mur15 = new Mur(600,150);
		mur16 = new Mur(300,100);
		mur17 = new Mur(200,100);
		mur18 = new Mur(400,350);
		 
	
		///////////////////////////
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
		solide(mur3);
		solide(mur4);
		solide(mur5);
		solide(mur6);
		solide(mur7);
		solide(mur8);
		solide(mur9);
		solide(mur10);
		solide(mur11);
		solide(mur12);
		solide(mur13);
		solide(mur14);
		solide(mur15);
		solide(mur16);
		solide(mur17);
		solide(mur18);


		
		//Déplacement  personnage
		this.deplacementPersoX();
		this.deplacementPersY();
		joueur.setX(persoX);
		joueur.setY(persoY);
		

		//placement du perso et du fond
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.joueur.marche("joueur", 5),joueur.getX(),joueur.getY(), null);//posistion du personnage
		
		//placement des murs
		g2.drawImage(this.mur.getImgMur(),700,350,null);
		g2.drawImage(this.mur2.getImgMur(),700,300,null);
		g2.drawImage(this.mur3.getImgMur(),this.mur3.getX(),this.mur3.getY(),null);
		g2.drawImage(this.mur4.getImgMur(),this.mur4.getX(),this.mur4.getY(),null);
		g2.drawImage(this.mur5.getImgMur(),this.mur5.getX(),this.mur5.getY(),null);
		g2.drawImage(this.mur6.getImgMur(),this.mur6.getX(),this.mur6.getY(),null);
		g2.drawImage(this.mur7.getImgMur(),this.mur7.getX(),this.mur7.getY(),null);
		g2.drawImage(this.mur8.getImgMur(),this.mur8.getX(),this.mur8.getY(),null);
		g2.drawImage(this.mur9.getImgMur(),this.mur9.getX(),this.mur9.getY(),null);
		g2.drawImage(this.mur10.getImgMur(),this.mur10.getX(),this.mur10.getY(),null);
		g2.drawImage(this.mur11.getImgMur(),this.mur11.getX(),this.mur11.getY(),null);
		g2.drawImage(this.mur12.getImgMur(),this.mur12.getX(),this.mur12.getY(),null);
		g2.drawImage(this.mur13.getImgMur(),this.mur13.getX(),this.mur13.getY(),null);
		g2.drawImage(this.mur14.getImgMur(),this.mur14.getX(),this.mur14.getY(),null);
		g2.drawImage(this.mur15.getImgMur(),this.mur15.getX(),this.mur15.getY(),null);
		g2.drawImage(this.mur16.getImgMur(),this.mur16.getX(),this.mur16.getY(),null);
		g2.drawImage(this.mur17.getImgMur(),this.mur17.getX(),this.mur17.getY(),null);
		g2.drawImage(this.mur18.getImgMur(),this.mur18.getX(),this.mur18.getY(),null);
	
		
		
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
