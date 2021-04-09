package GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.text.DefaultEditorKit.CopyAction;
import Donjon.Donjon;
import Donjon.Position;



public class ServerDonjon extends Thread {
    private ArrayList<Socket> jouers;
    private PrintWriter out = null;
    private BufferedReader in =null;
    private ArrayList<Position> listMur = new ArrayList<Position>();
    private ArrayList<Position> jPosHistorique = new ArrayList<Position>();
    private ArrayList<Position> listPg = new ArrayList<Position>();
    private ArrayList<Position> potion = new ArrayList<Position>();
	private int x;
	private int y;
    
//***CONTRUCTEUR***//
	public ServerDonjon() {
		try {
			 this.jouers=new ArrayList<Socket>();
			 this.x = largeur();
			 this.y =hauteur();
			 
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	public void run() {
		int rx=50*(this.x+2)+15;
		int ry=50*(this.y+2)+35;
		for(int i =0;i<jouers.size();i++) {
		try {
			out = new PrintWriter(jouers.get(i).getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			out.print(rx);
			out.flush();
		} catch (Exception e) {e.printStackTrace();}
		try {
			out.print(ry);
			out.flush();
		} catch (Exception e) {e.printStackTrace();}
		}
		while(true) {
			
		}
	}

		

	public Donjon getMap() {
		return this.scene1;
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
		this.jouers.add(s);
	}
	
	public Integer nJouers() {
		return this.jouers.size();
	}
	
}
