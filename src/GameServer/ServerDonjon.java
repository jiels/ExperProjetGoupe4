package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import Donjon.CommandException;
import Donjon.Main;


public class ServerDonjon extends Thread {
	private Socket socket;
    private PrintStream out;
    private BufferedReader in;
    private Main map;
    
    
    
//***CONTRUCTEUR***//
	public ServerDonjon() {
		try {
            this.socket=socket;
            this.map = new Main();
            out = new PrintStream( socket.getOutputStream() );
            in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
//***METHODES***//
	@SuppressWarnings("static-access")
	public void run() {
		try {
			map.start();
			while(true) {
				String commande = in.readLine();
				if(commande.length()==4) {
					
				}
				else {
					throw new CommandException("commande inconnue");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	


	
	
	
}
