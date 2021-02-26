package Donjon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
	private PrintStream out = null;
	private BufferedReader in =null;
	private Socket socket;
	
	//Constructor
	public Client() {
		try {
			socket = new Socket("127.0.0.1", 6112);
			out= new PrintStream(socket.getOutputStream());
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch( IOException a) {
				a.printStackTrace();
				}
	}
	
	
	//Methods
	
	
	public boolean touche(String commandes) throws CommandException {
		/*Cette méthode permet de vérifier que le joueur a bien entre 4 actions
		 * et que ce sont bien les commandes z,q,s,d qui on été saisie .
		 * 
		 *Si l'une des deux conditions n'est pas respecter elle émaint une CommandException.
		 */
		boolean vf = false;
		if(commandes.length()==4) {
			for (int i=0;i < commandes.length() ;i++) {
				if(String.valueOf( commandes.charAt(i)).equals("z")||String.valueOf( commandes.charAt(i)).equals("q")||String.valueOf( commandes.charAt(i)).equals("d")||String.valueOf( commandes.charAt(i)).equals("s")) {
					vf =true;
				}
				else {
					vf=false;
					throw new CommandException("Une ou plusieur des commandes entrer son invalide les commandes son :"
							+ "z=avancer, s=reculer, q= aller a gauched =aller a droite");
				}
			}
		}
		else {
			throw new CommandException();	
		}
	 	return vf;
	 }
	
	
	
	public  void loop(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String commande = sc.next();
			out.println(commande);
			String reponse;
			try {
				touche(commande);
				reponse = in.readLine();
				System.out.println(reponse);
				
			}catch(IOException | CommandException e){
				System.out.println(e.getMessage());
				loop();
				}
		}
	}
	


	public static void main(String argv[]) {
        Client c =new Client();
        c.loop();
    }
}

