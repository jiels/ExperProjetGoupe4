package GameServer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import Donjon.Donjon;
import Donjon.Main;


public class ServerDonjon extends Thread {
    private Socket socket;
    private PrintStream out;
    private BufferedReader in;
	private Donjon scene;
	private int x;
	private int y;
    
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
			x = Integer.valueOf(a);
			if(x>=10 && x<=20) {
				String b=in.readLine();
				y = Integer.valueOf(b);
				if(y>=10&&y<=14) {
					int rx=50*(x+2)+15;
					int ry=50*(y+2)+35;
					JFrame frame = new JFrame("the dungeon of hope");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(rx,ry);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setAlwaysOnTop(true);
					scene = new Donjon(rx,ry);
					frame.setContentPane(scene);
					frame.setVisible(true);
				}
			}
		} catch (IOException  e) {e.printStackTrace();}
		
		try {
			out.println("commandes: z=devant s=derrière q=gauche d=droit v= utiliser potion");
		} catch (Exception e) {e.getStackTrace();}
		
		try {
			while(true) {
				String cmd = in.readLine();
				if(!cmd.isEmpty()) {
					getMap().ServerClavier(cmd);
				}
				out.println(getMap().info());
				out.flush();
			}
			
		} catch (Exception e) {}
		
		
		
		
		
		
			
	}

		

	public Donjon getMap() {
		return this.scene;
	}

	
	
}
