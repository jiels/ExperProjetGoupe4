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
	
	public Client() throws ConnectException{
		try {
			socket = new Socket("127.0.0.1", 6112);
			out= new PrintStream(socket.getOutputStream());
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch( IOException a) {
				a.printStackTrace();
				}
	}
	
	
	
	public  void loop(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String commande = sc.next();
			out.println(commande);
			String reponse;
			try {
			if(commande.length()==4) {
				reponse = in.readLine();
				System.out.println(reponse);
				}
			else {
				throw new CommandLengthExeption();}
			}catch(IOException | CommandLengthExeption e){
				System.out.println(e.getMessage());
				loop();
				}
		}
	}
	


	public static void main(String argv[]) throws ConnectException {
        Client c =new Client();
        c.loop();
    }
}

