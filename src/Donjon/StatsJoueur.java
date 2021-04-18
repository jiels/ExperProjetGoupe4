package Donjon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class StatsJoueur implements Serializable{
	
	private static final long serialVersionUID = -4785070648054522221L;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in ;
	private Position position;
	private int vie = 5;
	private int potion = 0;
	private ArrayList<Position> hisposition= new ArrayList<Position>();
	private ArrayList<Position> listMurTouch = new ArrayList<Position>();
    private ArrayList<Position> listPgTouch = new ArrayList<Position>();

	public StatsJoueur(Socket  id ,Position p) {
		this.socket=id;
		this.position = p;
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
	        in = new ObjectInputStream(socket.getInputStream());
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



	public void plusVie(int vie) {
		if(this.vie<5) {
			this.vie += vie;
		}
	}
	public void moinVie() {
		if(this.vie>0) {
			this.vie -= 1;
		}
	}

	public String getInfo() {
		String inf = "vie: "+this.vie+" Posion: "+this.potion;
		return inf ;
	}

	public void plusPotion() {
		this.potion += 1;
	}
	public void moinPotion() {
		if(this.potion>0) {
			this.potion -=1;
		}
	}

	public void setPosition(Position position) {
		this.position = position;
	}


	public ObjectOutputStream getOut() {
		return out;
	}


	public ObjectInputStream getIn() {
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
	
	
	
	
	
	
	

}
