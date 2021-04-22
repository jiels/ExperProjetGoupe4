package Donjon;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class StatsJoueur implements Serializable{
	
	private static final long serialVersionUID = -4785070648054522221L;
	private Socket socket;
	private DataOutputStream out;
	private DataInputStream in ;
	private Position position;
	private int vie = 5;
	private int potion = 0;
	private ArrayList<Position> hisposition= new ArrayList<Position>();
	private ArrayList<Position> listMurTouch = new ArrayList<Position>();
    private ArrayList<Position> listPgTouch = new ArrayList<Position>();
    private ArrayList<Position> cle = new ArrayList<Position>();
    private boolean gagné =false;
    private boolean perdu =false;
    private String log ="";

	public void setGagné(boolean gagné) {
		this.gagné = gagné;
	}


	public void setPerdu(boolean perdu) {
		this.perdu = perdu;
	}



	public StatsJoueur(Socket  id ,Position p) {
		this.socket=id;
		this.position = p;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			out.flush();
	        in = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
		
	}
	
	
	
	public void setJoueur(StatsJoueur p) {
		this.position = p.position;
		this.hisposition = p.hisposition;
		this.listMurTouch = p.listMurTouch;
		this.listPgTouch = p.listPgTouch;
		this.vie = p.vie;
		this.potion = p.potion;
	}

	public Socket  getSocket() {
		return socket;
	}

	public Position getPosition() {
		return position;
	}

	public ArrayList<Position> getHispositiont() {
		return hisposition;
	}
	
	
	public void setHispositiont(Position e ) {
			hisposition.add(e);
	}


	public int getVie() {
		return this.vie;
	}
	public void plusVie(int vie) {
		if(this.vie<6) {
			this.vie += vie;
		}
	}
	public void moinVie() {
		if(this.vie>=1) {
			this.vie -= 1;
		}
	}

	public String getInfo() {
		String inf = "Vie: "+vie+" Potions: "+potion;
		return inf ;
	}
	
	

	public void plusPotion() {
		this.setLog("Vous avez trouvé une potion: +1 potion");
		this.potion += 1;
	}
	public void moinPotion() {
		if(this.potion>0&&vie<5) {
			this.potion -=1;
			this.setLog("Vous avez utilisé une potion: +1 vie -1 potion");
			plusVie(1);
		}else {
			this.setLog("Vous ne pouvez pas utiliser de potion :(");
		}
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	public DataOutputStream getOut() {
		return out;
	}


	public DataInputStream getIn() {
		return in;
	}


	public ArrayList<Position> getListMurTouch() {
		return listMurTouch;
	}
	public void addMu(Position p) {
		listMurTouch.add(p);
	}


	public void setListMurTouch(ArrayList<Position> listMurTouch) {
		this.listMurTouch = listMurTouch;
	}


	public ArrayList<Position> getListPgTouch() {
		return listPgTouch;
	}


	public void setListPgTouch(ArrayList<Position> listPgTouch) {
		this.listPgTouch = listPgTouch;
	}
	
	
	public void setCle(Position p) {
		cle.add(p);
	}




	public ArrayList<Position> getCle() {
		return cle;
	}



	public boolean isGagné() {
		return gagné;
	}



	public boolean isPerdu() {
		return perdu;
	}



	public String getLog() {
		return log;
	}


	public void resetLog() {
		log ="";
		
	}

	public void setLog(String log) {
		this.log += "\n"+log;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
