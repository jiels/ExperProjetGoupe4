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
	private Socket client;
	private Server server;
    private Main map;
    private Scanner inputStream;
    private PrintWriter outputStream;
    
//***CONTRUCTEUR***//
	public ServerDonjon(Socket client,Server server) {
		this.client=client;
		this.server = server;
		
	}
	

public void run() {
	try {
		inputStream = new Scanner(client.getInputStream());
		while(true) {
			String commande = inputStream.toString();
			this.map.getScene().setComd(commande);
			outputStream.print(map.info());
			}
		} catch (IOException e) {
			e.printStackTrace();
			}
	}

}
