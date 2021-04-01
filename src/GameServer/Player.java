package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Donjon.CommandException;



public class Player implements Runnable  {
private String id;
private Scanner inputStream;
private PrintWriter outputStream;
private Socket socket;



//***CONSTRUCTEUR***//
public Player(){
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	//PSEUDO DU JOUEUR
		System.out.print("Veiller entre un Pseudo: ");
		id = sc.next();
	//CONNEXION AU SERVER 
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		} catch (IOException e) {
			System.out.println("Une erreur c'est produit lors de la connexion du joueur au server");
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
		inputStream = new Scanner(socket.getInputStream());
		outputStream = new PrintWriter(socket.getOutputStream());
	} catch (IOException e1) {e1.printStackTrace();System.err.println("inputStream ou outputStream erreur");}

	while(true) {;
		try {
			System.out.println("Veillez entrer vos action :");
			String commande = t.next();
			String commande2="";
			commande2=maj(commande);
			if(commande(commande2)) {
				outputStream.println(commande2);
				
			}
			System.out.println(id+":"+ inputStream.nextLine());
			
		} catch (CommandException e) {
			e.getMessage();
			}
	}
	
}
	
public static void main(String argv[]) {
    new Player().run();
}	
	


}
