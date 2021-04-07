package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

import Donjon.CommandException;



public class Player{
private Scanner sc;
private String id;
private PrintWriter out = null;
private BufferedReader in =null;
private Socket socket;
private int xm;
private int ym;
private ClientDonjon map;


//***CONSTRUCTEUR***//
public Player(){
	sc = new Scanner(System.in);
	System.out.print("Veiller entre un Pseudo: ");
	id = sc.next();
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		in = new BufferedReader(new InputStreamReader( socket.getInputStream() ) );
        out= new PrintWriter(socket.getOutputStream());
		System.out.println("\nBienvenue a toi explorateur!");
	}catch (IOException e) {
		System.out.println("Une erreur c'est produit lors de la connexion du joueur au server");
		System.exit(1);
		}
    
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
			e+=a.charAt(i);
		}
		
	}
	return e;
}

public boolean commande(String a){
	boolean correct = false;
	if(a.length()==4) {
		for (int i=0;i<a.length();i++) {
			if(String.valueOf(a.charAt(i)).equals("Z")||String.valueOf(a.charAt(i)).equals("Q")||String.valueOf(a.charAt(i)).equals("S")||String.valueOf(a.charAt(i)).equals("D")||String.valueOf(a.charAt(i)).equals("V")) {
				correct = true;
			}
			else {
				correct = false;}
			}
	}
	return correct;	
}

public void run() {
	try {
		int i = Integer.valueOf(in.readLine());
		this.xm=i;
	} catch (Exception e) {e.printStackTrace();}
	try {
		int i = Integer.valueOf(in.readLine());
		this.ym=i;
	} catch (Exception e) {e.printStackTrace();}
	
	while(true) {
		
	}
}


public static void main(String argv[]) {
   new Player().run();

}	
	


}
