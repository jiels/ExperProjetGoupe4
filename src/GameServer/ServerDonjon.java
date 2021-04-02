package GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Donjon.Main;


public class ServerDonjon extends Thread {
    private Socket socket;
    private PrintStream out;
    private BufferedReader in;
    private Main map;
    
//***CONTRUCTEUR***//
	public ServerDonjon(Socket socket) {
		try {
			 this.socket= socket;
			 out = new PrintStream( socket.getOutputStream() );
	         in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
			
		}catch (Exception e) {e.printStackTrace();}
	}
	
	
	
	public void run() {
		try {
			String a = in.readLine();
			int x = Integer.valueOf(a);
			if(x>=10 && x<=20) {
				String b=in.readLine();
				int y = Integer.valueOf(b);
				if(y>=10&&y<=14) {
					int rx=50*(x+2)+15;
					int ry=50*(y+2)+35;
					this.map= new Main(rx,ry);
				}
			}
		} catch (IOException  e) {e.printStackTrace();}
		
		try {
			out.println("commandes: z=devant s=derrière q=gauche d=droit v= utiliser potion");
		} catch (Exception e) {e.getStackTrace();}
		
			
			
		}

		

	public Main getMap() {
		return this.map;
	}

	
	
}
