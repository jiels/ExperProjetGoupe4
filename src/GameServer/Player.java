package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import Donjon.CommandException;



public class Player{
private Scanner sc;
private String id;
private PrintWriter out = null;
private BufferedReader in =null;
private Socket socket;



//***CONSTRUCTEUR***//
public Player(){
	sc = new Scanner(System.in);
	System.out.print("Veiller entre un Pseudo: ");
	id = sc.next();
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
		out= new PrintWriter(socket.getOutputStream());
		System.out.println("\nBienvenue a toi explorateur!");
	} catch (IOException e) {
		System.out.println("Une erreur c'est produit lors de la connexion du joueur au server");
		System.exit(1);}
    
}
//***GETTER&SETTE***//


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
				throw new CommandException("L'une des de vos action na pas été reconue");}
			}
	}
	else {
		throw new CommandException("Le nombre d'ation prévu est inh correcte veillez entre 4 futures action");
		}
	return correct;
		
	
	
}

public void run() {
	try {
		System.out.print("Tout d'abord saisi la taille de la map en largeur (un entier entre 10 et 20) : ");
		String x=sc.next();
		if(Integer.valueOf(x)>=10&&Integer.valueOf(x)<=20) {
			out.println(x);
			out.flush();
		}
		System.out.print("Ensuite saisi la taille de la map en hauteur (un entier entre  10 et 14) :");
		String y = sc.next();
		if(Integer.valueOf(y)>=10&&Integer.valueOf(y)<=20) {
			out.println(y);
			out.flush();
			}
		
		}catch (Exception e) {e.getStackTrace();}
	
	try {
		String a = in.readLine() ;
		System.err.println("\n"+a);
	} catch (IOException e) {e.printStackTrace();}
	
	

	
}

public static void main(String argv[]) {
   new Player().run();

}	
	


}
