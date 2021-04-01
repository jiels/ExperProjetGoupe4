package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
	
	private int xAlea;
	private int yAlea;
	private int persoX;
	private int x;
	private int persoY;
	private int y;
	
	private ArrayList<Mur>listMur = new ArrayList<>();
	
	private int nbMur;
	
	private static int sortieX;
	private static int sortieY;
	
	
	
	private Jouer joueur;

	
	//initialisation mur//
	private Mur murExte;
	private Mur testMur;
	/////////////////
	//initialisation coeur//
	private PotionVie p1;
	private PotionVie p2;
	private PotionVie p3;
	
	//initialisation bombe//
	private Bombe b1;
	
	private Pierre pierre;

	private int xScene;
	private int yScene;
	
	
//***CONSTRUCTEUR***//
	public Donjon(int xScene, int yScene) {
		super();
		this.xFond1 = -50;
		this.persoX = xScene-115;
		this.persoY = yScene-125;
		this.xAlea= xScene;
		this.yAlea= yScene;
		this.xScene = xScene;
		this.yScene = yScene;
		this.setSortieX(50*genererInt((xScene-115)/100,(xScene-115)/50)); // où doit-être la sortie ? random ? si random : = 50*genererInt(0,xScene-115/50);
		this.setSortieY(50*genererInt((yScene-135)/100,(yScene-135)/50)); // si je la veux en haut je peux choisir de ne prendre que entre xscene/2 et xscene
		//Fond
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		
		//Joueur
		joueur = new Jouer(this.persoX,this.persoY);
		
		//Placement Murs
		murExte = new Mur(0,0);
		
		testMur = new Mur(100,100);
		
		
		//addMur(Mur)
		nbMur=((xAlea-115)/50)*((yAlea-135)/50)/5 ;// Si on décide de 1/5 de murs xscene=50*(x+2)+15 yscene50*(y+2)+35 
		addNbMur(nbMur);

		//Placement Coeur
		p1 =new PotionVie(750, 100);
		p2 =new PotionVie(150, 50);
		p3 =new PotionVie(350, 300);
	
		//Placement Bombe
		b1 = new Bombe(700, 400);
		

		
		///////////////////////////
		
		/*this.setFocusable(true);
		this.requestFocusInWindow();*/
		
		
		Thread ecranRefresh = new Thread(new SceenRefresh(this));
		ecranRefresh.start();}
	 
	
	
	
//***METHODES***//
	private void addMur(Mur m) {
		listMur.add(m);
	}

	private void addNbMur(int nbmur) { // Alea des murs
		int x;
		int y;
	for(int i=0; i<nbmur; i++) {
		x=50*genererInt(1,(xAlea-115)/50);
		y=50*genererInt(1,(yAlea-135)/50);
		Mur a = new Mur(x,y);
		if(x!=persoX&&y!=persoY&&listMur.contains(a)==false) {//marche pas : 2 murs peuvent avoir la meme coordonnée
		addMur(a);												// pour l'instant pas grave
		}
		else {
		i=i-1;	
		}
		} 
	}
	
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
		
		//ServerClavier(this.comd);
		//Collision solide
		
		Iterator<Mur> it1 = listMur.iterator();
		while(it1.hasNext()) {
			solide(it1.next());
		}
		
		solide(testMur);
		
	
	
	
	
		
		// Ramasse coeur
		joueur.ramaserPotion(p1);
		joueur.ramaserPotion(p2);
		joueur.ramaserPotion(p3);
		
		// bombe explose


		
		//Déplacement personnage
		
		this.deplacementPersoX();
		this.deplacementPersoY();
		joueur.setX(persoX);
		joueur.setY(persoY);
		

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
		
				int m = 0;
				g2.drawImage(this.murExte.getImgMur(),50,50,null);
				for (int i = 0; i < getListMur().size(); i++) {
				      m++;
				      
				      g2.drawImage(this.murExte.getImgMur(),getListMur().get(i).getX(),getListMur().get(i).getY(),null);
				} 
		
	
			
		//affichage des coeurs
		g2.drawImage(this.p1.getImgpotion(),this.p1.getX(),this.p1.getY(),null);
		g2.drawImage(this.p1.getImgpotion(),this.p2.getX(),this.p2.getY(),null);
		g2.drawImage(this.p1.getImgpotion(),this.p3.getX(),this.p3.getY(),null);
		
		//affichage des bombes
		g2.drawImage(this.b1.getImgBombe(),this.b1.getX(),this.b1.getY(),null);
		
		
			
	}


	public void ServerClavier(String cd) {
		if(!cd.isEmpty()) {
		for (int i=0;i<cd.length();i++) {
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_D) {//Doit
				setPersoX(getPersoX()+50);
				getJoueur().setMarche(true);
				getJoueur().setVersDroite(true);
				getJoueur().setVersGauche(false);
				getJoueur().setVersB(false);
				repaint();
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Q) {//Gauche
				setPersoX(getPersoX()-50);
				getJoueur().setMarche(true);
				getJoueur().setVersGauche(true);
				getJoueur().setVersDroite(false);
				getJoueur().setVersB(false);
				repaint();
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_S) {//bas
				setPersoY(getPersoY()+50);
				getJoueur().setVersDroite(false);
				getJoueur().setVersGauche(false);
				getJoueur().setVersB(true);
				getJoueur().setVersH(false);
				getJoueur().setMarche(true);
				repaint();
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Z) {//haut
				setPersoY(getPersoY()-50);
				getJoueur().setVersDroite(false);
				getJoueur().setVersGauche(false);
				getJoueur().setVersH(true);
				getJoueur().setMarche(true);
				getJoueur().setVersB(false);
				repaint();
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_V) {
				getJoueur().usePotion();
				repaint();
				
			}
			getJoueur().setMarche(false);
			
		}
		}
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


	public int getSortieX() {
		return sortieX;
	}


	public void setSortieX(int sortieX) {
		this.sortieX = sortieX;
	}


	public int getSortieY() {
		return sortieY;
	}


	public void setSortieY(int sortieY) {
		this.sortieY = sortieY;
	}

	public Jouer getJoueur() {
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
