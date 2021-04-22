package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Objet.Bombe;
import Objet.Cle;
import Objet.Mur;
import Objet.Objet;
import Objet.Sol;
import Personnages.Joueur;



public class Donjon extends JPanel {
/**
	 * 
	 */
	private static final long serialVersionUID = 3318991719492400521L;
	//***INITIALISATION***//
	private ImageIcon solFond;
	private Image  sol;
	private int xFond1;
	
	@SuppressWarnings("unused")
	private int xAlea;
	@SuppressWarnings("unused")
	private int yAlea;
	private int persoX;
	private int x;
	private int persoY;
	private int y;
	
	private ArrayList<Position>listMur = new ArrayList<>();
	private ArrayList<Position>listBombe = new ArrayList<>();
	private ArrayList<Position>listSol = new ArrayList<>();
	private ArrayList<Position>listCle = new ArrayList<>();
	
	@SuppressWarnings("unused")
	private int nbMur;
	
	private static int sortieX;
	private static int sortieY;
	
	private Joueur joueur;

	private Mur murExte;

	private int xScene;
	private int yScene;
	private int xS;
	private int yS;
	private Sol sol1;
	

//***CONSTRUCTEUR***//
	public Donjon(int xScene, int yScene) {
		super();
		this.xFond1 = -50;
		this.persoX = (50*genererInt((xScene-115)/75,(xScene-115)/50))+50; 
		this.persoY = (50*genererInt((yScene-135)/75,(yScene-135)/50))+50;
		this.xAlea= xScene;
		this.yAlea= yScene;
		this.xScene = xScene;
		this.yScene = yScene;
		this.xS = persoX;
		this.yS = persoY;
		
		
		//Fond
		solFond = new ImageIcon("src/images/sol.png");
		this.sol = this.solFond.getImage();
		
		//Joueur
		joueur = new Joueur(this.persoX,this.persoY);
		
		//Placement Murs
		murExte = new Mur(0,0);
		sol1 = new Sol(0,0);
		
		
		

		
		///////////////////////////
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		
		Thread ecranRefresh = new Thread(new SceneRefresh(this));
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
	
	public void deplacementPersoY() {
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
	
	public void solide(Objet objet) {
		
		if(joueur.collision(objet)) {
			if(joueur.isVersDroite()) {
				this.persoX=objet.getX()-50;
			}
			if(joueur.isVersGauche()) {
				this.persoX=objet.getX()+50;
			}
			if(joueur.isVersH()) {
				this.persoY=objet.getX()+50;
			}
			if(joueur.isVersB()) {
				this.persoY=objet.getX()-50;
			}
		}
	}
	public int genererInt(int borneInf, int borneSup){
		   Random random = new Random();
		   int nb;
		   nb = borneInf+random.nextInt(borneSup-borneInf);
		   return nb;
		}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g;

		g2.drawImage(this.sol1.getImgSol(),xS,yS,null);
		if(!listSol.isEmpty()) {
			for (int i = 0; i < listSol.size(); i++) {
					Sol s = new Sol(listSol.get(i).getX(),listSol.get(i).getY());
			      g2.drawImage(s.getImgSol(),s.getX(),s.getY(),null);
			} }
		if(!listBombe.isEmpty()) {
			for (int i = 0; i < listBombe.size(); i++) {
					Bombe b = new Bombe(listBombe.get(i).getX(),listBombe.get(i).getY());
			      g2.drawImage(b.getImgBombe(),b.getX(),b.getY(),null);
			} }
		//placement du perso et du fond
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.joueur.marche("joueur", 5),joueur.getX(),joueur.getY(), null);//posistion du personnage
		
		//placement des murs de délimitation de la zone d'action du personnage
		
				for(int x=0; x<=(getXScene()-50);x=x+50) {
					for(int y = 0 ; y<=(getYScene()-50);y=y+50) {
						if(x==0||(x>=(getXScene()-100)&&x<(getXScene()-50)||y==0||(y>=(getYScene()-100)&&y<(getYScene()-50)))) {
							g2.drawImage(this.murExte.getImgMur(),x,y,null);
					}	
				}}
				//540 = 10 et 550 = 11
				// 1/5 = 20% de part de murs ? 
				
		
				
		//AFFICHAGE DES MURS
				
		
				if(!listMur.isEmpty()) {
				for (int i = 0; i < listMur.size(); i++) {
						Mur m = new Mur(listMur.get(i).getX(),listMur.get(i).getY());
				      g2.drawImage(m.getImgMur(),m.getX(),m.getY(),null);
				} }
				if(!listCle.isEmpty()) {
					for (int i = 0; i < listCle.size(); i++) {
							Cle s = new Cle(listCle.get(i).getX(),listCle.get(i).getY());
					      g2.drawImage(s.getImgCle(),s.getX(),s.getY(),null);
					} }
			
	}



	
	
//***GETTER & SETTER***//
	public String info() {
		String str = "Vie:"+this.getJoueur().getVie()+" Potion:"+this.getJoueur().getPotion();
		return str;}
	
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

	public void setListMur(ArrayList<Position> p) {
		this.listMur=p;
	}
	
	public void setListBombe(ArrayList<Position> p) {
		this.listBombe=p;
	}
	
	public void setListSol(ArrayList<Position> p) {
		this.listSol=p;
	}
	
	public void setlistCle(Position p) {
		this.listCle.add(p);
	}

	public int getSortieX() {
		return sortieX;
	}


	@SuppressWarnings("static-access")
	public void setSortieX(int sortieX) {
		this.sortieX = sortieX;
	}


	public int getSortieY() {
		return sortieY;
	}


	@SuppressWarnings("static-access")
	public void setSortieY(int sortieY) {
		this.sortieY = sortieY;
	}

	public Joueur getJoueur() {
		return joueur;
	}


	public void setPersoX(int persoX) {
		this.persoX = persoX;
	}




	public int getPersoX() {
		return persoX;
	}




	public int getPersoY() {
		return persoY;
	}




	public void setPersoY(int persoY) {
		this.persoY = persoY;
	}



	


	
	
	


	
	
	
	
	
	
	
	
	
	
}
