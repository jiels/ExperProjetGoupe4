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
    private boolean gagné =false;
    private boolean perdu =false;

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
		if(this.vie<5) {
			this.vie += vie;
		}
	}
	public void moinVie() {
		if(this.vie>=-1) {
			this.vie -= 1;
		}
	}

	public String getInfo() {
		String inf = "vie: "+this.vie+" Potions: "+this.potion;
		return inf ;
	}
	
	

	public void plusPotion() {
		this.potion += 1;
	}
	public void moinPotion() {
		if(this.potion>0) {
			this.potion -=1;
			plusVie(1);
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



	public boolean isGagné() {
		return gagné;
	}



	public boolean isPerdu() {
		return perdu;
	}
	
	
	
	
	
	
	
	
	

}
