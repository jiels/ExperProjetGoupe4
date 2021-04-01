package GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import Donjon.Main;


public class ServerDonjon extends Thread {
	Scanner clavier = new Scanner(System.in);
	private Socket client;
    private Main map;
    
//***CONTRUCTEUR***//
	public ServerDonjon(Socket client,int x,int y) throws IOException  {
			 this.client= client;
			 int a=50*(x+2)+15;
			 int b=50*(y+2)+35;
			 map = new Main(a,b);
	}
		public void run() {
			try {
				String cd;
				String rp;
				while(true) {
					try {
					cd = (new BufferedReader( new InputStreamReader(this.client.getInputStream() ) )).readLine();
					getMap().getScene().ServerClavier(cd);
					}catch (IOException e) {
						e.getStackTrace();
					}
					
					
					try {
					rp = getMap().info();
					(new PrintStream( this.client.getOutputStream() )).println(rp);}
					catch (Exception e) {
						e.getStackTrace();
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}


	public Main getMap() {
		return map;
	}

	
	
}
