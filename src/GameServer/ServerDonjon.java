package GameServer;


import java.awt.event.KeyEvent;
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
	private  int id ;
	boolean gagné=false;
    
//***CONTRUCTEUR***//
	public ServerDonjon(Server server) {
		
		super("ServerDonjon");
		try {
			final int id =server.getParties().size();
			this.id= id;
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
		addPotion();
		System.out.println(cle);
		Position t = new Position(rx, ry);
		for(int i =0;i<joueurs.size();i++) {
			try {
				WriteObjectToFile(t);
				File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
					File file3 = new File("src\\TmpServer\\"+id+"Objets.dit");
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
						if(joueurs.get(i).getSocket().isClosed()||!joueurs.get(i).getSocket().isConnected()) {
							joueurs.remove(i);
						}
						
					if(joueurs.get(i).getSocket().isConnected()) {
					int fileByte = joueurs.get(i).getIn().readInt();
					if(fileByte>0) {
						byte[] file2 = new byte[fileByte];
						joueurs.get(i).getIn().readFully(file2, 0, file2.length);
						savefile(file2);
					}
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
						File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
						File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
						File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
						WriteObjectToFile(tm);
						File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
						
						int tt =0;
						
							joueurs.get(i).plusVie(1);
							for (int c =0;c<joueurs.size();c++) {
								if(joueurs.get(i).getPosition().compareTo(joueurs.get(c).getPosition())==0){
									if(!joueurs.get(c).isPerdu()) {
										tt+=1;
										joueurs.get(i).moinVie();
										}
									}
								if(tt>1) {
									joueurs.get(i).setLog("Vous êtes a la même position qu'un autre joueur: -1 vie");	
									}
							
								if(joueurs.get(i).getVie()<=0){
									joueurs.get(i).setPerdu(true);
									
									} 
								}
							tt=0;
							
							
							
							
							
							
							try {
								String tmlog = joueurs.get(i).getLog();
								WriteObjectToFile(tmlog);
								File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
							joueurs.get(i).resetLog();

						String tmpinfo="";
						if(!joueurs.get(i).isPerdu()&&!joueurs.get(i).isGagné()) {
						tmpinfo = joueurs.get(i).getInfo();
						}
						if (joueurs.get(i).isPerdu()) {
							tmpinfo = "Vous avez PERDU !";
						}
						if (joueurs.get(i).isGagné()) {
							tmpinfo = " Vous avez GAGNÉ !";
						}
						WriteObjectToFile(tmpinfo);
						File file = new File("src\\TmpServer\\"+id+"Objets.dit");
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
					
					
					
					}else {joueurs.remove(i);}
					}
				
				for( int h=0 ;h<joueurs.size();h++) {
					if(joueurs.get(h).isGagné()) {
						for(int s=0 ;s<joueurs.size();s++) {
							try {
								joueurs.get(s).setCle(cle);
								Position tmlog = joueurs.get(s).getCle().get(0);
								WriteObjectToFile(tmlog);
								File file = new File("src\\TmpServer\\"+id+"Objets.dit");
								final File[] filetosent = new File[1];
								filetosent[0]=file;
								FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
								byte[]filebyte = new byte[(int)filetosent[0].length()];
								fileInputStream.read(filebyte);
								joueurs.get(s).getOut().writeInt(filebyte.length);
								joueurs.get(s).getOut().flush();
								joueurs.get(s).getOut().write(filebyte);
								joueurs.get(s).getOut().flush();
							}catch (Exception e) {e.printStackTrace();}
						}
						winer=true;
					}
					if (joueurs.get(h).isPerdu()) {
						joueurs.get(h).getOut().close();
						joueurs.get(h).getIn().close();
					}
					
				}
				
				
				
				
				if(joueurs.size()==0) {
					this.interrupt();
				}
				}
			this.interrupt();
			
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
	
	public void resetpPg(Position g) {
		boolean t = false;
		int x;
		int y;
		while(!t) {
			x=50*genererInt(1,(rx-115)/50);
			y=50*genererInt(1,(ry-135)/50);
			Position a = new Position(x,y);
			if(a.compareTo(cle)==-1&&comparListMur(a)&&comparJoueurs(a)&&comparListpg(a)){//marche pas : 2 murs peuvent avoir la meme coordonnée
				g.setX(a.getX());
				g.setY(a.getY());
				t=true;
				}
			
		}
		
	}
	
	
	private void addPotion() { // Alea des murs
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
		
	
				
		if(!cd.isEmpty()&&!p.isPerdu()) {	
		for (int i=0;i<cd.length();i++) {
			if(iterateur==0) {
				iterateur+=1;
				joueurs.get(i).setHispositiont(joueurs.get(i).getPosition());
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_D) {//Doit
				p.setLog("Vous etes allé a droite");
				int tmpx =p.getPosition().getX()+50;
				Position tmpp = new Position(tmpx, p.getPosition().getY());
				p.setHispositiont(tmpp);
				if(tmpx<rx-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==tmpx&&listMur.get(e).getY()==p.getPosition().getY()) {
							tmpx=listMur.get(e).getX()-50;
							p.getListMurTouch().add(listMur.get(e));
							p.setLog("Vous avez touché un mur");
							e+=1;
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.setLog("Vous êtes tombé dans un piege: -1 vie");
							p.moinVie();
							e+=1;
							
						}
						}
					for(int e=0;e<listPotion.size();e++) {
						if(listPotion.get(e).compareTo(p.getPosition())==0) {
							p.plusPotion();
							resetpPg(listPotion.get(e));
							
						}
					}
					
					if(p.getPosition().compareTo(cle)==0) {
						p.setGagné(true);
						gagné=true;
					}
					if(gagné) {
						p.setPerdu(true);
					}
					p.getPosition().setX(tmpx);
		
					
					}
					
					
				}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Q) {//Gauche
				p.setLog("Vous êtes allé à gauche");
				int tmpx = p.getPosition().getX()-50;
				Position tmpp = new Position(tmpx, p.getPosition().getY());
				p.setHispositiont(tmpp);
				if(tmpx>=50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==tmpx&&listMur.get(e).getY()==p.getPosition().getY()) {
							tmpx=listMur.get(e).getX()+50;
							p.getListMurTouch().add(listMur.get(e));
							p.setLog("Vous avez touché un mur");
							e+=1;
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.setLog("Vous êtes tombé dans un piège: -1 vie");
							p.moinVie();
							e+=1;
						}}
					for(int e=0;e<listPotion.size();e++) {
						if(listPotion.get(e).compareTo(p.getPosition())==0) {
							p.plusPotion();
							resetpPg(listPotion.get(e));

							
						}
					}
					if(p.getPosition().compareTo(cle)==0) {
						p.setGagné(true);
						gagné=true;
					}
					if(gagné) {
						p.setPerdu(true);
					}

					p.getPosition().setX(tmpx);
					
				}
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_S) {//bas
				p.setLog("Vous êtes allé en bas");
				int tmpy = p.getPosition().getY()+50;
				Position tmpp = new Position(p.getPosition().getX(),tmpy);
				p.setHispositiont(tmpp);
				if(tmpy<ry-100) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==p.getPosition().getX()&&listMur.get(e).getY()==tmpy) {
							tmpy=listMur.get(e).getY()-50;
							p.getListMurTouch().add(listMur.get(e));
							p.setLog("Vous avez touché un mur");
							e+=1;
					
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.setLog("Vous êtes tombé dans un piège: -1 vie");
							p.moinVie();
							e+=1;
						}}
					for(int e=0;e<listPotion.size();e++) {
						if(listPotion.get(e).compareTo(p.getPosition())==0) {
							p.plusPotion();
							resetpPg(listPotion.get(e));
							
						}
					}
					if(p.getPosition().compareTo(cle)==0) {
						p.setGagné(true);
						gagné=true;
					}
					if(gagné) {
						p.setPerdu(true);
					}
		
					p.getPosition().setY(tmpy);
					
				
				}
				
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_Z) {//haut
				p.setLog("Vous êtes allé en haut");
				int tmpy = p.getPosition().getY()-50;
				Position tmpp = new Position(p.getPosition().getX(),tmpy);
				p.setHispositiont(tmpp);
				if(tmpy>=50) {
					for(int e=0;e<listMur.size();e++) {
						if(listMur.get(e).getX()==p.getPosition().getX()&&listMur.get(e).getY()==tmpy) {
							tmpy=listMur.get(e).getY()+50;
							p.getListMurTouch().add(listMur.get(e));
							p.setLog("Vous avez touché un mur");
							e+=1;
							
						}}
					for(int e=0;e<listPg.size();e++) {
						if(listPg.get(e).compareTo(p.getPosition())==0) {
							p.getListPgTouch().add(listPg.get(e));
							p.setLog("Vous êtes tombé dans un piège: -1 vie");
							p.moinVie();
							e+=1;
						}}
					for(int e=0;e<listPotion.size();e++) {
						if(listPotion.get(e).compareTo(p.getPosition())==0) {
							p.plusPotion();
							resetpPg(listPotion.get(e));
							
						}
					}
					if(p.getPosition().compareTo(cle)==0) {
						p.setGagné(true);
						gagné=true;
					}
					if(gagné) {
						p.setPerdu(true);
					}
					
					p.getPosition().setY(tmpy);
	
				}
				
				
			}
			if(String.valueOf(cd.charAt(i)).hashCode()==KeyEvent.VK_V) {
				p.moinPotion();
				
				
				
			}
			
			
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
					return t;
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
				return a;
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
					return a;
					
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
					return a;
				}
			}
		}
		return a;
	}
	
	public Object ReadObjectFromFile() {
		 
	    try {

	        FileInputStream fileIn = new FileInputStream("src\\TmpServer\\"+id+"Objetsrecu.dit");
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
 
            FileOutputStream fileOut = new FileOutputStream("src\\TmpServer\\"+id+"Objets.dit");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(serObj);
            objectOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	public void savefile(byte[] e) throws FileNotFoundException, IOException {
		try (
				FileOutputStream stream = new FileOutputStream("src\\TmpServer\\"+id+"Objetsrecu.dit")) {
			    stream.write(e);
			}
	}





	public ArrayList<StatsJoueur> getJoueurs() {
		return joueurs;
	}



}
