package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;





public class Server{
	public static final int PORT=6112;
	private ArrayList<Socket> clientList;
	
	public Server() {
		ServerSocket serverSocket = null;
        try {
        	System.out.println("Demarage du serveur.......");
        	serverSocket = new ServerSocket(PORT);
            serverSocket.setReuseAddress(true); 
            System.out.println("serveur connecté!");
        } catch (IOException e) {
            System.err.println("Le serveur n'a pas pu démarrer");
            System.err.println(e);
            System.exit(1);
        } 
        clientList= new ArrayList<Socket>();
        
        while(true) {
        	Socket client;
        	try {
        		client = serverSocket.accept();
        		clientList.add(client);
        		ServerDonjon joueur=new ServerDonjon(client);
        		joueur.start();
				
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
