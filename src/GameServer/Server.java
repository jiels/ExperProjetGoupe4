package GameServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;




public class Server{
	public static final int PORT=6112;
	private ArrayList<Socket> clientList;
	private ArrayList<ServerDonjon> parties;
	private ServerSocket serverSocket;
	
	
	public Server() {
        try {
        	System.out.println("Demarage du serveur.......");
        	serverSocket = new ServerSocket(PORT);
            serverSocket.setReuseAddress(true);
            clientList= new ArrayList<Socket>();
            parties = new ArrayList<ServerDonjon>();
            System.out.println("serveur connecté!");
        } catch (IOException e) {
            System.err.println("Le serveur n'a pas pu démarrer");
            System.err.println(e);
            System.exit(1);
        } 
        while(true) {
        	try {
        		ServerDonjon party =new ServerDonjon();
        		Socket client = serverSocket.accept();
        		party.newPlayer(client);
        		clientList.add(client);
        		while(party.nJouers()<2) {
        			if(clientList.contains(client)) {
        				client = serverSocket.accept();
        			}
        			else {
        				party.newPlayer(client);
        				clientList.add(client);
        				parties.add(party);
        				party.start();
        				System.out.println(parties.size());
        			}
        			
        		}	
        
				
			} catch (IOException e) {
				System.err.println("Une erreure est arrivée lorsqu'un joueur a tenté de se connecter... ");
                System.err.println(e);
			}
        }
	}

	public static void main(String argv[]) throws UnknownHostException, IOException {
        new Server();
    }


	
	
}
