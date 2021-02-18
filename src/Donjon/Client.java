package Donjon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Client {
	private static PrintStream out = null;
	private static BufferedReader in =null;
	private Socket socket;
	
	public Client(){
		try {
			socket = new Socket("127.0.0.1", 6112);
			out= new PrintStream( socket.getOutputStream() );
            in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch(IOException a) {
				a.printStackTrace();
				}
	}
	
	
	
	public static  void loop(){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			String commande = sc.next();
			String reponse;
			out.println(commande);
			try {
			if(commande.length()==4) {
				reponse = in.readLine();
				System.out.println(reponse);
				}
			else {
				throw new CommandLengthExeption();}
			}catch(IOException|CommandLengthExeption e){
				System.out.println(e.getMessage());
				System.exit(1);
				loop();
				}
		}
	}
	

	@SuppressWarnings("static-access")
	public static void main(String argv[]) {
        Client c =new Client();
        c.loop();
    }
}

