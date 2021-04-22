package GameServer;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;


import Donjon.Actions;
import Donjon.CommandException;
import Donjon.Position;
import Donjon.StatsJoueur;



public class Player{
private String id;
private DataOutputStream out = null;
private DataInputStream in =null;
private Socket socket;
private int xm;
private int ym;
private ClientDonjon map;
private Scanner sc;

//***CONSTRUCTEUR***//
public Player(){
	sc = new Scanner(System.in);
	System.out.print("Veiller entre un Pseudo(ne doit contenir  que des lettres): ");
	id = sc.next();
	try {
		System.out.println("connexion au server........");
		socket = new Socket("127.0.0.1", 6112);
		out = new DataOutputStream(socket.getOutputStream());
        out.flush();
        in = new DataInputStream(socket.getInputStream());
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
		int fileByte = in.readInt();
		if(fileByte>0) {
			byte[] file = new byte[fileByte];
			in.readFully(file, 0, file.length);
			savefile(file);
		}
		Position i = (Position) ReadObjectFromFile();
		map = new ClientDonjon(id,i.getX(), i.getY());
		Thread t = new Thread(map);
		t.start();
		System.out.println("La partie a démarer !" );
	} catch (Exception e) {e.printStackTrace();}
	
	try {
		Position p =new Position(map.getScene().getPersoX(), map.getScene().getPersoY());
		WriteObjectToFile(p);
		File file = new File("src\\TmpJoueur\\"+"id"+"Objets.dit");
		final File[] filetosent = new File[1];
		filetosent[0]=file;
		FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
		byte[]filebyte = new byte[(int)filetosent[0].length()];
		fileInputStream.read(filebyte);
		out.writeInt(filebyte.length);
		out.flush();
		out.write(filebyte);
		out.flush();
		
	} catch (Exception e) {e.printStackTrace();}
	
	try {
		int fileByte = in.readInt();
		if(fileByte>0) {
			byte[] file3 = new byte[fileByte];
			in.readFully(file3, 0, file3.length);
			savefile(file3);
		}
			ArrayList<Position>pp= extracted(ReadObjectFromFile());
			this.map.getScene().setListSol(pp);
	}catch (Exception e) {e.printStackTrace();}
	System.err.println("Les action posible sont| z:devant s:dèrière q:gauche d:doite v:utiliser positionde vie");
	
	try{
	while(true) {
		
			System.out.print("\nVeillez entrer quatre action :");
			String comd = sc.next();
			String comd2 = maj(comd);
			
			while(!commande(comd2)) {
				System.out.print("action non reconnu réessayer : ");
				String comd3 = sc.next();
				comd2 = maj(comd3);
			}
			WriteObjectToFile(comd2);
			File file = new File("src\\TmpJoueur\\"+"id"+"Objets.dit");
			final File[] filetosent = new File[1];
			filetosent[0]=file;
			FileInputStream fileInputStream= new FileInputStream(filetosent[0].getAbsolutePath());
			byte[]filebyte = new byte[(int)filetosent[0].length()];
			fileInputStream.read(filebyte);
			out.writeInt(filebyte.length);
			out.flush();
			out.write(filebyte);
			out.flush();
			System.out.println("En attent des autres joueurs......");
			
	
			
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
					Position pp = (Position)ReadObjectFromFile();
					this.map.setJoueurPosition(pp);
			}catch (Exception e) {sc.close();e.printStackTrace();}
			
			
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
				ArrayList<Position> listeM =extracted(ReadObjectFromFile());
				this.map.getScene().setListMur(listeM);		
			}catch (Exception e) {sc.close();e.printStackTrace();}
			
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
				ArrayList<Position> historique =extracted(ReadObjectFromFile());
				this.map.getScene().setListSol(historique);	
			}catch (Exception e) {sc.close();e.printStackTrace();}
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
				ArrayList<Position> pg =extracted(ReadObjectFromFile());
				this.map.getScene().setListBombe(pg);
			}catch (Exception e) {sc.close();e.printStackTrace();}
			
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
				String info =(String) ReadObjectFromFile();
				System.err.println(info);	
			}catch (Exception e) {sc.close();e.printStackTrace();}
			
			try {
				int fileByte = in.readInt();
				if(fileByte>0) {
					byte[] file3 = new byte[fileByte];
					in.readFully(file3, 0, file3.length);
					savefile(file3);
				}
				String log =(String) ReadObjectFromFile();
				System.out.println(log);	
			}catch (Exception e) {sc.close();e.printStackTrace();}}
	
} catch (Exception e) {
	try {
		System.err.println("vous etes deconnecte");
		sc.close();
		out.close();
		in.close();
		socket.close();
	} catch (IOException i) {
		i.printStackTrace();}
}	
}


@SuppressWarnings("unchecked")
private ArrayList<Position> extracted(Object readObject) {
	return (ArrayList<Position>)readObject;
}

public Object ReadObjectFromFile() {
	 
    try {

        FileInputStream fileIn = new FileInputStream("src\\TmpJoueur\\"+id+"Objetsrecu.dit");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);

        Object obj = objectIn.readObject();

        objectIn.close();
        return obj;

    } catch (Exception ex) {
        ex.printStackTrace();
        return null;
    }
}

public void WriteObjectToFile(Object serObj) { 
    try {
        FileOutputStream fileOut = new FileOutputStream("src\\TmpJoueur\\"+"id"+"Objets.dit");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(serObj);
        objectOut.close();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}


public void savefile(byte[] e) throws FileNotFoundException, IOException {
	try (
			FileOutputStream stream = new FileOutputStream("src\\TmpJoueur\\"+id+"Objetsrecu.dit")) {
		    stream.write(e);
		}
}

public static void main(String argv[]) {
 Player a= new Player();
  a.run();
}	
	


}
