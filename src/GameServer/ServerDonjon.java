package GameServer;


import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFileChooser;

import Donjon.Actions;
import Donjon.Position;
import Donjon.StatsJoueur;



public class ServerDonjon extends Thread {
    ArrayList<StatsJoueur> joueurs=new ArrayList<StatsJoueur>();
    private ArrayList<Position> listMur = new ArrayList<Position>();
    private ArrayList<Position> listPg = new ArrayList<Position>();
    private ArrayList<Position> listPotion = new ArrayList<Position>();
	private int x;
	private int y;
	private int rx;
	private int ry;
	private Position cle ;
	private boolean winer=false;
	private int iterateur= 0;
    
//***CONTRUCTEUR***//
	public ServerDonjon(Server server) {
		super("ServerDonjon");
		try {
			 this.x = largeur();
			 this.y =hauteur();
			 rx=50*(this.x+2)+15;
			 ry=50*(this.y+2)+35;
			 
			 cle=positionCle();
		}catch (Exception e) {e.printStackTrace();}
	}
	
	

	
	
	public void run() {
		addMur();
		addPg();
		addPosion();
		Position t = new Position(rx, ry);
		for(int i =0;i<joueurs.size();i++) {
			try {
				WriteObjectToFile(t);
				File file = new File("src\\TmpServer\\Objets.dit");
				final File[] filetosent = new File[1];
				filetosent[0]=file;
				FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
				byte[]filebyte = new byte[(int)filetosent[0].length()];
				fileInputStream.read(filebyte);
				joueurs.get(i).getOut().writeInt(filebyte.length);
				joueurs.get(i).getOut().flush();
				joueurs.get(i).getOut().write(filebyte);
				joueurs.get(i).getOut().flush();
			
				int fileByte = joueurs.get(i).getIn().readInt();
				if(fileByte>0) {
					byte[] file2 = new byte[fileByte];
					joueurs.get(i).getIn().readFully(file2, 0, file2.length);
					savefile(file2);
					
				}
				Position pp = (Position)ReadObjectFromFile();
				joueurs.get(i).setPosition(pp);
				joueurs.get(i).setHispositiont(pp);
				try {
					WriteObjectToFile(joueurs.get(i).getHispositiont());
					File file3 = new File("src\\TmpServer\\Objets.dit");
					final File[] filetosent3 = new File[1];
					filetosent3[0]=file3;
					FileInputStream fileInputStream3= new FileInputStream(filetosent3[0].getAbsolutePath());
					byte[]filebyte3 = new byte[(int)filetosent3[0].length()];
					fileInputStream3.read(filebyte3);
					joueurs.get(i).getOut().writeInt(filebyte3.length);
					joueurs.get(i).getOut().flush();
					joueurs.get(i).getOut().write(filebyte3);
					joueurs.get(i).getOut().flush();
					
				} catch (Exception e) {e.printStackTrace();}
			} catch (Exception e) {e.printStackTrace();}
		
		}
		
		try {	
			while(!winer) {
				for(int i =0;i<joueurs.size();i++) {
					if(joueurs.get(i).getSocket().isConnected()) {
					int fileByte = joueurs.get(i).getIn().readInt();
					if(fileByte>0) {
						byte[] file2 = new byte[fileByte];
						joueurs.get(i).getIn().readFully(file2, 0, file2.length);
						savefile(file2);
					}
	
					String cmd = (String)ReadObjectFromFile();
					StatsJoueur p =  ServerClavier(cmd, joueurs.get(i));
					
					joueurs.get(i).setJoueur(p);
					}else {
						joueurs.remove(i);
					}
				}
				for(int i =0;i<joueurs.size();i++) {
					if(joueurs.get(i).getSocket().isConnected()) {
					try {
						Position jp = joueurs.get(i).getPosition();
						WriteObjectToFile(jp);
						File file = new File("src\\TmpServer\\Objets.dit");
						final File[] filetosent = new File[1];
						filetosent[0]=file;
						FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
						byte[]filebyte = new byte[(int)filetosent[0].length()];
						fileInputStream.read(filebyte);
						joueurs.get(i).getOut().writeInt(filebyte.length);
						joueurs.get(i).getOut().flush();
						joueurs.get(i).getOut().write(filebyte);
						joueurs.get(i).getOut().flush();
					}catch (Exception e) {e.printStackTrace();}
					
					try {
						ArrayList<Position>tm = joueurs.get(i).getListMurTouch();
						WriteObjectToFile(tm);
						File file = new File("src\\TmpServer\\Objets.dit");
						final File[] filetosent = new File[1];
						filetosent[0]=file;
						FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
						byte[]filebyte = new byte[(int)filetosent[0].length()];
						fileInputStream.read(filebyte);
						joueurs.get(i).getOut().writeInt(filebyte.length);
						joueurs.get(i).getOut().flush();
						joueurs.get(i).getOut().write(filebyte);
						joueurs.get(i).getOut().flush();
					}catch (Exception e) {e.printStackTrace();}
					
					try {
						ArrayList<Position>tm = joueurs.get(i).getHispositiont();
						WriteObjectToFile(tm);
						File file = new File("src\\TmpServer\\Objets.dit");
						final File[] filetosent = new File[1];
						filetosent[0]=file;
						FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
						byte[]filebyte = new byte[(int)filetosent[0].length()];
						fileInputStream.read(filebyte);
						joueurs.get(i).getOut().writeInt(filebyte.length);
						joueurs.get(i).getOut().flush();
						joueurs.get(i).getOut().write(filebyte);
						joueurs.get(i).getOut().flush();
					}catch (Exception e) {e.printStackTrace();}
					
					try {
						ArrayList<Position>tm = joueurs.get(i).getListPgTouch();
						System.out.println(tm);
						WriteObjectToFile(tm);
						File file = new File("src\\TmpServer\\Objets.dit");
						final File[] filetosent = new File[1];
						filetosent[0]=file;
						FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
						byte[]filebyte = new byte[(int)filetosent[0].length()];
						fileInputStream.read(filebyte);
						joueurs.get(i).getOut().writeInt(filebyte.length);
						joueurs.get(i).getOut().flush();
						joueurs.get(i).getOut().write(filebyte);
						joueurs.get(i).getOut().flush();
					}catch (Exception e) {e.printStackTrace();}
					
					try {
						
						joueurs.get(i).plusVie(1);
						
						String tmpinfo = joueurs.get(i).getInfo();
						if (joueurs.get(i).isPerdu()) {
							tmpinfo = "Vous avez perdu !";
						}
						WriteObjectToFile(tmpinfo);
						File file = new File("src\\TmpServer\\Objets.dit");
						final File[] filetosent = new File[1];
						filetosent[0]=file;
						FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
						byte[]filebyte = new byte[(int)filetosent[0].length()];
						fileInputStream.read(filebyte);
						joueurs.get(i).getOut().writeInt(filebyte.length);
						joueurs.get(i).getOut().flush();
						joueurs.get(i).getOut().write(filebyte);
						joueurs.get(i).getOut().flush();
						
						if (joueurs.get(i).isPerdu()) {
							joueurs.get(i).getOut().close();
							joueurs.get(i).getIn().close();
						}
					}catch (Exception e) {e.printStackTrace();}
					
					}else {joueurs.remove(i);}
					}
				}
			
		}catch (Exception e) {e.printStackTrace();}

	}

	
	public Position positionCle() {
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

	public void newPlayer(Socket s) throws IOException {
		this.joueurs.add(new StatsJoueur(s,new Position(0,0)));
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
		listMur.add(new Position(0, 0));
		for(int i=0; i<nbmur; i++) {
			x=50*genererInt(1,(rx-115)/50);
			y=50*genererInt(1,(ry-135)/50);
			Position a = new Position(x,y);
			if(a.compareTo(cle)==-1&&comparListMur(a)&&comparJoueurs(a)) {//marche pas : 2 murs peuvent avoir la meme coordonnée
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
			if(a.compareTo(cle)==-1&&comparListMur(a)&&comparJoueurs(a)&&comparListpg(a)){//marche pas : 2 murs peuvent avoir la meme coordonnée
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
			if(a.compareTo(cle)==-1&&comparListMur(a)&&comparJoueurs(a)&&comparListpg(a)&&comparListpg(a)) {//marche pas : 2 murs peuvent avoir la meme coordonnée
				listPotion.add(a);
				}
			else {
				i=i-1;	
				}
			} 
		}
	

	public StatsJoueur ServerClavier(String cd ,StatsJoueur p) {
		for(int o=0;o<joueurs.size();o++) {
			if(joueurs.get(o).getPosition().compareTo(p.getPosition())==0){
				p.moinVie();
			}
		}
		p.plusVie(1);
		
		if(p.getVie()==0){
			p.setPerdu(true);
			
			}
		
		if(!cd.isEmpty()&&!p.isPerdu()) {
			for(int o=0;o<joueurs.size();o++) {
				if(joueurs.get(o).getPosition().compareTo(p.getPosition())==0){
					p.moinVie();
				}
			}
			p.plusVie(1);
			
		for (int i=0;i<cd.length();i++) {
			if(iterateur==0) {
				iterateur+=1;
				joueurs.get(i).setHispositiont(joueurs.get(i).getPosition());
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_D) {//Doit
				int tmpx =p.getPosition().getX()+50;
				Position tmpp = new Position(tmpx, p.getPosition().getY());
				p.setHispositiont(tmpp);
				if(tmpx<rx-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==tmpx&&listMur.get(e).getY()==p.getPosition().getY()) {
							tmpx=listMur.get(e).getX()-50;
							p.getListMurTouch().add(listMur.get(e));
							e+=1;
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.moinVie();
							e+=1;
							
						}}
					
					p.getPosition().setX(tmpx);
		
					
					}
					
					
				}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Q) {//Gauche
				int tmpx = p.getPosition().getX()-50;
				Position tmpp = new Position(tmpx, p.getPosition().getY());
				p.setHispositiont(tmpp);
				if(tmpx>=50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==tmpx&&listMur.get(e).getY()==p.getPosition().getY()) {
							tmpx=listMur.get(e).getX()+50;
							p.getListMurTouch().add(listMur.get(e));
							e+=1;
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.moinVie();
							e+=1;
						}}

					p.getPosition().setX(tmpx);
					
				}
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_S) {//bas
				int tmpy = p.getPosition().getY()+50;
				Position tmpp = new Position(p.getPosition().getX(),tmpy);
				p.setHispositiont(tmpp);
				if(tmpy<ry-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==p.getPosition().getX()&&listMur.get(e).getY()==tmpy) {
							tmpy=listMur.get(e).getY()-50;
							p.getListMurTouch().add(listMur.get(e));
							e+=1;
					
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.moinVie();
							e+=1;
						}}
		
					p.getPosition().setY(tmpy);
					
				
				}
				
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Z) {//haut
				int tmpy = p.getPosition().getY()-50;
				Position tmpp = new Position(p.getPosition().getX(),tmpy);
				p.setHispositiont(tmpp);
				if(tmpy>=50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==p.getPosition().getX()&&listMur.get(e).getY()==tmpy) {
							tmpy=listMur.get(e).getY()+50;
							p.getListMurTouch().add(listMur.get(e));
							e+=1;
							
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.moinVie();
							e+=1;
						}}
					
					p.getPosition().setY(tmpy);
	
				}
				
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_V) {
				p.moinPotion();
				
				
				
			}
			
			
		}
		
		for(int o=0;o<joueurs.size();o++) {
			if(joueurs.get(o).getPosition().compareTo(p.getPosition())==0){
				p.moinVie();
			}
		}
		p.plusVie(1);
		
		if(p.getVie()==0){
			p.setPerdu(true);
			
			}
		
	}
		return p;
	}

	public boolean comparListMur(Position p) {
		boolean t = true;
		if(!listMur.isEmpty()) {
			for(int i=0;i<listMur.size();i++) {
				if(listMur.get(i).compareTo(p)==-1) {
					t =true;
				}
				else {
					t=false;
				}
			}
		}
		return t;
	}
	
	public boolean comparJoueurs(Position p) {
		boolean a = false;
		for(int i=0;i<joueurs.size();i++) {
			if(joueurs.get(i).getPosition().compareTo(p)==-1) {
				a =true;
			}
			else{
				a=false;
			}
		}
		return a;
	}
	
	public boolean comparListpg(Position p) {
		boolean a =true;
		if(!listPg.isEmpty()) {
			for(int i =0;i<listPg.size();i++) {
				if(listPg.get(i).compareTo(p)==-1) {
					a =true;
				}
				else {
					a=false;
					
				}
			}
		}
		return a;
	}

	public boolean comparListePosion(Position p) {
		boolean a=true;
		if(!listPotion.isEmpty()) {
			for(int i=0;i<listPotion.size();i++){
				if(listPotion.get(i).compareTo(p)==-1) {
					a=true;
				}
				else {
					a=false;
				}
			}
		}
		return a;
	}
	
	public Object ReadObjectFromFile() {
		 
	    try {

	        FileInputStream fileIn = new FileInputStream("src\\TmpServer\\Objetsrecu.dit");
	        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

	        Object obj = objectIn.readObject();

	        objectIn.close();
	        return obj;

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public void WriteObjectToFile(Object serObj) {
		 
        try {
 
            FileOutputStream fileOut = new FileOutputStream("src\\TmpServer\\Objets.dit");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	public void savefile(byte[] e) throws FileNotFoundException, IOException {
		try (
				FileOutputStream stream = new FileOutputStream("src\\TmpServer\\Objetsrecu.dit")) {
			    stream.write(e);
			}
	}
}
