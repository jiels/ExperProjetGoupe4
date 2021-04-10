package GameServer;


import java.awt.event.KeyEvent;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import Donjon.Actions;
import Donjon.Position;
import Donjon.PositionJoueur;



public class ServerDonjon extends Thread {
    private ArrayList<PositionJoueur> joueurs=new ArrayList<PositionJoueur>();
    private ArrayList<Position> listMur = new ArrayList<Position>();
    private ArrayList<Position> listPg = new ArrayList<Position>();
    private ArrayList<Position> listMurTouch = new ArrayList<Position>();
    private ArrayList<Position> listPgTouch = new ArrayList<Position>();
    private ArrayList<Position> listPotion = new ArrayList<Position>();
	private int x;
	private int y;
	private int rx;
	private int ry;
	private Position sortie ;
    
//***CONTRUCTEUR***//
	public ServerDonjon() {
		try {
			 this.x = largeur();
			 this.y =hauteur();
			 rx=50*(this.x+2)+15;
			 ry=50*(this.y+2)+35;
			 sortie=PositionSortie();
			 addMur();
			 addPg();
			 addPosion();
		}catch (Exception e) {e.printStackTrace();}
	}
	
	

	
	
	public void run() {
		
		Position t = new Position(rx, ry);
		for(int i =0;i<joueurs.size();i++) {
			try {
				
				joueurs.get(i).getOut().writeObject(t);
				joueurs.get(i).getOut().flush();
				} catch (Exception e) {e.printStackTrace();}
			try {
				Position pp = (Position)joueurs.get(i).getIn().readObject();
				joueurs.get(i).setPosition(pp);
				joueurs.get(i).setHispositiont(pp);
			} catch (Exception e) {e.printStackTrace();}
		
		}
		while(true) {
			for(int i =0;i<joueurs.size();i++) {
				try {
					Actions cmd = (Actions)joueurs.get(i).getIn().readObject();
					ServerClavier(cmd.getCmd(), joueurs.get(i));
				} catch (Exception e) {e.printStackTrace();}
		}
		
			
			
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

	
	public Position PositionSortie() {
		return new Position((50*genererInt(2,(rx-115)/50))+50,(50*genererInt(2,(ry-135)/100))+50);
	}


	public Integer largeur() {
		ArrayList<Integer> xx = new ArrayList<Integer>();
		for(int i=10;i<=20;i++) {
			xx.add(i);
		}
		int x =(int) (Math.random()*xx.size());
		return xx.get(x);
	}
	
	public Integer hauteur() {
		ArrayList<Integer> yy = new ArrayList<Integer>();
		for(int i=10;i<=14;i++) {
			yy.add(i);
		}
		int y =(int) (Math.random()*yy.size());
		return yy.get(y);
	}

	public void newPlayer(Socket s) {
		this.joueurs.add(new PositionJoueur(s,new Position(0,0)));
	}
	
	public Integer nJouers() {
		return this.joueurs.size();
	}

	public int genererInt(int borneInf, int borneSup){
		   Random random = new Random();
		   int nb;
		   nb = borneInf+random.nextInt(borneSup-borneInf);
		   return nb;
		}
	
	private void addMur() { // Alea des murs
		int x;
		int y;
		int nbmur =((rx-115)/50)*((ry-135)/50)/5;
		for(int i=0; i<nbmur; i++) {
			x=50*genererInt(1,(rx-115)/50);
			y=50*genererInt(1,(ry-135)/50);
			Position a = new Position(x,y);
			if(y!=sortie.getY()&&x!= sortie.getX()&&x!=joueurs.get(i).getPosition().getX()&&y!=joueurs.get(i).getPosition().getY()&&listMur.get(i).compareTo(a)==-1) {//marche pas : 2 murs peuvent avoir la meme coordonnée
				listMur.add(a);
				}
			else {
				i=i-1;	
				}
			} 
		}
	
	private void addPg() { // Alea des murs
		int x;
		int y;
		int nbmur =((rx-115)/50)*((ry-135)/50)/10;
		for(int i=0; i<nbmur; i++) {
			x=50*genererInt(1,(rx-115)/50);
			y=50*genererInt(1,(ry-135)/50);
			Position a = new Position(x,y);
			if(y!=sortie.getY()&&x!= sortie.getX()&&x!=joueurs.get(i).getPosition().getX()&&y!=joueurs.get(i).getPosition().getY()&&listMur.get(i).compareTo(a)==-1&&listPg.get(i).compareTo(a)==-1) {//marche pas : 2 murs peuvent avoir la meme coordonnée
				listPg.add(a);
				}
			else {
				i=i-1;	
				}
			} 
		}
	
	
	private void addPosion() { // Alea des murs
		int x;
		int y;
		int nbmur =5;
		for(int i=0; i<nbmur; i++) {
			x=50*genererInt(1,(rx-115)/50);
			y=50*genererInt(1,(ry-135)/50);
			Position a = new Position(x,y);
			if(y!=sortie.getY()&&x!= sortie.getX()&&x!=joueurs.get(i).getPosition().getX()&&y!=joueurs.get(i).getPosition().getY()&&listMur.get(i).compareTo(a)==-1&&listPg.get(i).compareTo(a)==-1&&listPotion.get(i).compareTo(a)==-1) {//marche pas : 2 murs peuvent avoir la meme coordonnée
				listPotion.add(a);
				}
			else {
				i=i-1;	
				}
			} 
		}
	

	public void ServerClavier(String cd ,PositionJoueur p) {
		if(!cd.isEmpty()) {
		for (int i=0;i<cd.length();i++) {
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_D) {//Doit
				int tmpx =p.getPosition().getX()+50;
				if(tmpx<rx-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).compareTo(p.getPosition())==0) {
							listMurTouch.add(listMur.get(e));
							listMur.remove(e);
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							listPgTouch.add(listPg.get(e));
							listPg.remove(e);
						}}
					p.getPosition().setX(tmpx);
					p.setHispositiont(p.getPosition());
					}
					
					
				}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Q) {//Gauche
				int tmpx = p.getPosition().getX()-50;
				if(tmpx>50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).compareTo(p.getPosition())==0) {
							listMurTouch.add(listMur.get(e));
							listMur.remove(e);
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							listPgTouch.add(listPg.get(e));
							listPg.remove(e);
						}}
					p.getPosition().setX(tmpx);
					p.setHispositiont(p.getPosition());
				}
				/*getJoueur().setMarche(true);
				getJoueur().setVersGauche(true);
				getJoueur().setVersDroite(false);
				getJoueur().setVersB(false);
				repaint();*/
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_S) {//bas
				int tmpy = p.getPosition().getY()+50;
				if(tmpy<ry-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).compareTo(p.getPosition())==0) {
							listMurTouch.add(listMur.get(e));
							listMur.remove(e);
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							listPgTouch.add(listPg.get(e));
							listPg.remove(e);
						}}
					p.getPosition().setY(tmpy);
					p.setHispositiont(p.getPosition());
				}
				/*getJoueur().setVersDroite(false);
				getJoueur().setVersGauche(false);
				getJoueur().setVersB(true);
				getJoueur().setVersH(false);
				getJoueur().setMarche(true);
				repaint();*/
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Z) {//haut
				int tmpy = p.getPosition().getY()-50;
				if(tmpy>50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).compareTo(p.getPosition())==0) {
							listMurTouch.add(listMur.get(e));
							listMur.remove(e);
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							listPgTouch.add(listPg.get(e));
							listPg.remove(e);
						}}
					p.getPosition().setY(tmpy);
					p.setHispositiont(p.getPosition());
				}
				
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_V) {
				p.moinPosion();
				
				
			}
		}
	}
	}
	
	
}
