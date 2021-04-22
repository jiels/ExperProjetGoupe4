package GameServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;




public class Server{
	public static final int PORT=6112;
	private ArrayList<Socket> clientList;
	@SuppressWarnings("unused")
	private ArrayList<Thread> parties;
	private ServerSocket serverSocket;
	
	public Server() {
        try {
        	System.out.println("Demarage du serveur.......");
        	serverSocket = new ServerSocket(PORT);
            serverSocket.setReuseAddress(true);
            clientList= new ArrayList<Socket>();
            parties = new ArrayList<Thread>();
            System.out.println("serveur connecté!");
            int n =0;
            while(true) {
            	ServerDonjon party =new ServerDonjon(this);
            	while(n<2) {
            		Socket client = serverSocket.accept();
            		party.newPlayer(client);
            		clientList.add(client);
            		n+=1;
            		}
            	parties.add(party);
            	party.start();
            	n=0;
            	for (int i =0 ; i< parties.size();i++) {
            		if(!parties.get(i).isInterrupted()) {
            			parties.remove(i);
            		}
            	}
        
        }	
  
        }catch (IOException e) {e.printStackTrace();}
        
                
        }


	public ArrayList<Thread> getParties() {
		return parties;
	}


	public static void main(String argv[]) throws UnknownHostException, IOException {
        new Server();
    }


	
	
}
