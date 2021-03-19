package GameServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.Scanner;

import Donjon.CommandException;



public class Player implements Serializable {
private static final long serialVersionUID = -987725587799656168L;
private PrintStream out = null;
private BufferedReader in =null;
private Socket socket;


//***CONSTRUCTEUR***//
public Player() {
    try {
        socket = new Socket("127.0.0.1", 6112);
        out= new PrintStream( socket.getOutputStream() );
        in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
    } catch (IOException e) {
    	e.printStackTrace();
    }
}

//***METHODE***//

public boolean commande(String a) throws CommandException {
	boolean correct = false;
	if(a.length()==4) {
		for (int i=0;i<a.length();i++) {
			if(String.valueOf(a.charAt(i)).equals("z")||String.valueOf(a.charAt(i)).equals("q")||String.valueOf(a.charAt(i)).equals("s")||String.valueOf(a.charAt(i)).equals("d")||String.valueOf(a.charAt(i)).equals("v")) {
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

public void loop() {
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	while(true) {
		String commande = sc.next();
		try {
			if(commande(commande)) {
			out.print(commande);
			String reponse;
			try {
				reponse = in.readLine();
                System.out.println("Reponse serveur : "+reponse);	
				} catch (Exception e) {
					e.getStackTrace();
					System.out.println("La connexion est perdue !");
					System.exit(1);}
			}
		} catch (CommandException e) {
			e.getMessage();
			this.loop();}
	}
	
}
	
public static void main(String argv[]) {
    Player p =new Player();
    p.loop();
}	
	


}
