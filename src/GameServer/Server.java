package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;





public class Server implements Runnable {
	public static final int PORT=6112;
	private ServerSocket serverSocket;
	private ArrayList<Socket> clientList;
	private Socket client;

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
			//ATTENT DU CLIENT
			Socket client = null;
			try {
			client = serverSocket.accept();
			this.client = client;
			clientList.add(client);
			BufferedReader in = new BufferedReader( new InputStreamReader( client.getInputStream() ) );

			if(!clientList.contains(client));{
				System.out.println("Nouveux client accepter...");
				System.out.println("Nombre d'utilisateur: " + clientList.size());
				String srt= in.readLine();
				System.out.println(srt);
				int x = Integer.valueOf(String.valueOf(srt.charAt(0))+""+String.valueOf(srt.charAt(1)));
				int y = Integer.valueOf(String.valueOf(srt.charAt(3))+""+String.valueOf(srt.charAt(4)));
				ServerDonjon donjon = new ServerDonjon(client,x,y);
				donjon.run();
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	public Socket getClient() {
		return client;
	}

	public static void main(String argv[]) throws UnknownHostException, IOException {
        new Server().run();
    }


	
	
}
