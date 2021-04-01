package GameServer;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;





public class Server implements Runnable {
	 public static final int PORT=6112;
	 private ServerSocket serverSocket;
	 private ArrayList<Socket> clientList;

	public Server() {
        try {
            serverSocket = new ServerSocket(PORT);
            serverSocket.setReuseAddress(true);
        } catch (IOException e) {
            System.err.println("Le serveur n'a pas pu démarrer");
            System.err.println(e);
            System.exit(1);
        } 
        clientList = new ArrayList<Socket>();
	}
	
	public void run() {
		System.out.println("en attante de clients ...");
		while(true) {
			//ATTENT DU CLIENT
			Socket client;
			try {
			client = serverSocket.accept();
			clientList.add(client);
			if(clientList.contains(client));{
				System.out.println("Nouveux client accepter..." + client.getRemoteSocketAddress());
				System.out.println("Nombre d'utilisateur: " + clientList.size());
				ServerDonjon donjon = new ServerDonjon(client,this);
				donjon.run();}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void main(String argv[]) throws UnknownHostException, IOException {
        new Server().run();
    }


	
	
}
