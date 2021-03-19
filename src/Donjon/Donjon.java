package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objet.PotionVie;
import Objet.Bombe;
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
	
	private ArrayList<Mur>listMur = new ArrayList<>();
	
	private int nbMur;
	
	private void addMur(Mur m) {
		listMur.add(m);
	}
	private void addNbMur(int nbmur) { // Alea des murs
	for(int i=0; i<nbmur; i++) {
		addMur(new Mur(0,0));
		} 
	}
	public Jouer joueur;
	//initialisation mur//

	
	/////////////////
	//initialisation coeur//
	public PotionVie p1;
	public PotionVie p2;
	public PotionVie p3;
	
	//initialisation bombe//
	public Bombe b1;
	
	public Pierre pierre;

	public int xScene;
	public int yScene;
	
	
//***CONSTRUCTEUR***//
	public Donjon(int xScene, int yScene) {
		super();
		this.xFond1 = -50;
		this.persoX = 750;
		this.persoY = 400;
		this.xScene = xScene;
		this.yScene = yScene;
		
		//Fond
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		
		//Joueur
		joueur = new Jouer(this.persoX,this.persoY);
		
		//Placement Murs
		nbMur=((xScene-115)/50)*((yScene-135)/50)/5 ;  // Si on d�cide de 1/5 de murs xscene=50*(x+2)+15 yscene50*(y+2)+35 
		addNbMur(nbMur);
		
		
		//addMur(Mur )

		//Placement Coeur
		p1 =new PotionVie(750, 100);
		p2 =new PotionVie(150, 50);
		p3 =new PotionVie(350, 300);
	
		//Placement Bombe
		b1 = new Bombe(700, 400);
		
		///////////////////////////
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new ServerClavier());
		
		Thread ecranRefresh = new Thread(new SceenRefresh());
		ecranRefresh.start();}
	 
	
	
	
//***METHODES***//
	public void deplacementPersoX() {
		if(this.persoX==xScene-100) {
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
		else if(this.persoY==yScene-110) {
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
	
	// Creation solide mur 
	

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;
		//System.out.println(getListMur().size());
		
		
		//Collision solide
		
		
		// Ramasse coeur
		joueur.ramaserPotion(p1);
		joueur.ramaserPotion(p2);
		joueur.ramaserPotion(p3);
		
		// bombe explose


		
		//D�placement personnage
		this.deplacementPersoX();
		this.deplacementPersY();
		joueur.setX(persoX);
		joueur.setY(persoY);
		

		//placement du perso et du fond
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.joueur.marche("joueur", 5),joueur.getX(),joueur.getY(), null);//posistion du personnage
		
		//affichage des murs
		//g2.drawImage(this.mur.getImgMur(),this.mur.getX(),this.mur.getY(),null);
		//g2.drawImage(this.mur2.getImgMur(),this.mur2.getX(),this.mur2.getY(),null);
		//g2.drawImage(this.mur3.getImgMur(),this.mur3.getX(),this.mur3.getY(),null);
		//g2.drawImage(this.mur4.getImgMur(),this.mur4.getX(),this.mur4.getY(),null);
		//g2.drawImage(this.mur5.getImgMur(),this.mur5.getX(),this.mur5.getY(),null);
		//g2.drawImage(this.mur6.getImgMur(),this.mur6.getX(),this.mur6.getY(),null);
		//g2.drawImage(this.mur7.getImgMur(),this.mur7.getX(),this.mur7.getY(),null);
		//g2.drawImage(this.mur8.getImgMur(),this.mur8.getX(),this.mur8.getY(),null);
		//g2.drawImage(this.mur9.getImgMur(),this.mur9.getX(),this.mur9.getY(),null);
		//g2.drawImage(this.mur10.getImgMur(),this.mur10.getX(),this.mur10.getY(),null);
		//g2.drawImage(this.mur11.getImgMur(),this.mur11.getX(),this.mur11.getY(),null);
		//g2.drawImage(this.mur12.getImgMur(),this.mur12.getX(),this.mur12.getY(),null);
		//g2.drawImage(this.mur13.getImgMur(),this.mur13.getX(),this.mur13.getY(),null);
		//g2.drawImage(this.mur14.getImgMur(),this.mur14.getX(),this.mur14.getY(),null);
		//g2.drawImage(this.mur15.getImgMur(),this.mur15.getX(),this.mur15.getY(),null);
		//g2.drawImage(this.mur16.getImgMur(),this.mur16.getX(),this.mur16.getY(),null);
		//g2.drawImage(this.mur17.getImgMur(),this.mur17.getX(),this.mur17.getY(),null);
		//g2.drawImage(this.mur18.getImgMur(),this.mur18.getX(),this.mur18.getY(),null);
	
		//affichage des coeurs
		g2.drawImage(this.p1.getImgpotion(),this.p1.getX(),this.p1.getY(),null);
		g2.drawImage(this.p1.getImgpotion(),this.p2.getX(),this.p2.getY(),null);
		g2.drawImage(this.p1.getImgpotion(),this.p3.getX(),this.p3.getY(),null);
		
		//affichage des bombes
		g2.drawImage(this.b1.getImgBombe(),this.b1.getX(),this.b1.getY(),null);
		
		//placement des murs de d�limitation de la zone d'action du personnage
		
		//for(int x=0; x<= 800;x=x+50) {
			//for(int y = 0 ; y<=450;y=y+50) {
				//if(x==0||x==800||y==0||y==450) {
				//g2.drawImage(this.mur.getImgMur(),x,y,null);
				
			//for(int x=0; x<getXScene();x=x+50) {
				//for(int y = 0 ; y<getYScene();y=y+50) {
					//if(x==0||(x>=(getXScene()-50)&&x<(getXScene())||y==0||(y>=(getYScene()-50)&&y<(getYScene())))) {
					//g2.drawImage(this.mur.getImgMur(),x,y,null);
					
		for(int x=0; x<=(getXScene()-50);x=x+50) {
			for(int y = 0 ; y<=(getYScene()-50);y=y+50) {
				if(x==0||(x>=(getXScene()-100)&&x<(getXScene()-50)||y==0||(y>=(getYScene()-100)&&y<(getYScene()-50)))) {
					//g2.drawImage(this.mur.getImgMur(),x,y,null);
			}	
		}}
			//System.out.println("xScene :"+getXScene()+"yScene :"+getYScene());
	}
//540 = 10 et 550 = 11
// 1/5 = 20% de part de murs ? 
	
	
	
	
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

	public int getXScene() {
		return xScene;
	}

	public void setXScene(int xScene) {
		this.xScene = xScene;
	}

	public int getYScene() {
		return yScene;
	}

	public void setYScene(int yScene) {
		this.yScene = yScene;
	}

	public ArrayList<Mur> getListMur() {
		return listMur;
	}


	
	
	
	
	
	
	
	
	
	
}
