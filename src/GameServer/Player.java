package GameServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


import Donjon.Actions;
import Donjon.CommandException;
import Donjon.Position;



public class Player{
private String id;
private ObjectOutputStream out = null;
private ObjectInputStream in =null;
private Socket socket;
private int xm;
private int ym;
private ClientDonjon map;
private Scanner sc;

//***CONSTRUCTEUR***//
public Player(){
	sc = new Scanner(System.in);
	System.out.print("Veiller entre un Pseudo: ");
	id = sc.next();
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
        in = new ObjectInputStream(socket.getInputStream());
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
	System.out.println("en attente d'autres joueurs.......");
	try {
		Position i = (Position) in.readObject();
		map = new ClientDonjon(id,i.getX(), i.getY());
		Thread t = new Thread(map);
		t.start();
		System.out.println("La partie a démarer !" );
	} catch (Exception e) {e.printStackTrace();}
	
	try {
		Position p =new Position(map.getScene().getPersoX(), map.getScene().getPersoY());
		out.writeObject(p);
		out.flush();
		
	} catch (Exception e) {e.printStackTrace();}
	System.err.println("Les action posible sont| z:devant s:dèrière q:gauche d:doite v:utiliser positionde vie");
	
	try {
	while(true) {
		
			System.out.print("\nVeillez entrer quatre action :");
			String comd = sc.next();
			String comd2 = maj(comd);
			
			while(!commande(comd2)) {
				System.out.print("action non reconnu réessayer : ");
				String comd3 = sc.next();
				comd2 = maj(comd3);
			}
			
			Actions ac =null;
			ac=new Actions(comd2);
			out.writeObject(ac);
			out.flush();
			System.out.println("En attent des autres joueurs......");
			
	
			
			System.out.println(map.getScene().getJoueur().getX()+" "+map.getScene().getJoueur().getY());
			Object p = in.readObject();
			Position pp = (Position)p;
			System.out.println(pp);
			this.map.setJoueurPosition(pp);
			
			Object readObject = in.readObject();
			ArrayList<Position> listeM = extracted(readObject);
			System.out.println(listeM);
			this.map.getScene().setListMur(listeM);	
			
			
			Object a = in.readObject();
			System.out.println((String)a);
		
	}
} catch (Exception e) {e.printStackTrace();}
	
}


@SuppressWarnings("unchecked")
private ArrayList<Position> extracted(Object readObject) {
	return (ArrayList<Position>)readObject;
}


public static void main(String argv[]) {
 Player a= new Player();
  a.run();

}	
	


}
