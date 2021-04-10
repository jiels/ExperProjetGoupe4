package Donjon;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

public class PositionJoueur implements Serializable{
	
	private static final long serialVersionUID = -4785070648054522221L;
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in ;
	private Position position;
	private int vie = 5;
	private int posion = 0;
	private ArrayList<Position> hisposition= new ArrayList<Position>() ;

	public PositionJoueur(Socket  id ,Position p) {
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

	public String getInfon() {
		String inf = "vie: "+this.vie+" Posion: "+this.posion;
		return inf ;
	}

	public void plusPosion() {
		this.posion += 1;
	}
	public void moinPosion() {
		if(this.posion>0) {
			this.posion -=1;
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
	
	
	
	
	
	
	

}
