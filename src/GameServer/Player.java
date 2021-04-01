package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import Donjon.CommandException;



public class Player{
private String id;
private PrintStream out = null;
private BufferedReader in =null;
private Socket socket;



//***CONSTRUCTEUR***//
public Player(){
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	System.out.print("Veiller entre un Pseudo: ");
	id = sc.next();
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		out = new PrintStream( socket.getOutputStream() );
		
		System.out.print("Saisir la taille de la map en largeur (entre 10 et 20) : ");
		String x=sc.next();
		System.out.print("Saisir la taille de la map en hauteur (entre 10 et 14) :");
		String y = sc.next();
		String xy =x+"|"+y;
		out.println(xy);;
	} catch (IOException e) {
		System.out.println("Une erreur c'est produit lors de la connexion du joueur au server");
		System.exit(1);
	}
	
    
}

//***METHODE***//
public String maj(String a) throws CommandException {
	String e="";
	for(int i=0;i<a.length();i++) {
		if(String.valueOf(a.charAt(i)).equals("z")) {
			e+="Z";
		}
		else if(String.valueOf(a.charAt(i)).equals("q")) {
			e+="Q";
		}
		else if(String.valueOf(a.charAt(i)).equals("s")) {
			e+="S";
		}
		else if(String.valueOf(a.charAt(i)).equals("d")) {
			e+="D";
		}
		else if(String.valueOf(a.charAt(i)).equals("v")) {
			e+="V";
		}
		else {
			throw new CommandException("Des commande inconnue on été entrer ");
		}
		
	}
	return e;
}

public boolean commande(String a) throws CommandException {
	boolean correct = false;
	if(a.length()==4) {
		for (int i=0;i<a.length();i++) {
			if(String.valueOf(a.charAt(i)).equals("Z")||String.valueOf(a.charAt(i)).equals("Q")||String.valueOf(a.charAt(i)).equals("S")||String.valueOf(a.charAt(i)).equals("S")||String.valueOf(a.charAt(i)).equals("V")) {
				correct = true;
			}
			else {
				correct = false;
				throw new CommandException("L'une des de vos action na pas été reconue");
				
			}
			
		}
	}
	else {
			throw new CommandException("Le nombre d'ation prévu est inh correcte veillez entre 4 futures action");
		}
	return correct;
		
	
	
}

public void run() {
	@SuppressWarnings("resource")
	Scanner t = new Scanner(System.in);
	try {
	while(true) {
		System.out.println("Veillez entrer vos action :");
		String commande = t.next();
		try {
			String commande2=maj(commande);
			if(commande(commande2)) {
				System.out.println("jjajaj");
				out.print(commande2);}
		}catch (CommandException e) {
			e.getMessage();}
		
		
		String reponse;
		try {
			System.out.println(11);
			reponse = in.readLine();
			System.out.println(this.id+": "+reponse);
		}catch (IOException e) {
			// TODO: handle exception
		}
		
		}
	}catch (Exception e1) {e1.getMessage();System.err.println("La connexion est perdue !");}
}
public static void main(String argv[]) {
    Player c =new Player();
    c.run();
}	
	


}
