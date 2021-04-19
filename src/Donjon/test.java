package Donjon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

public class test {


	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		
		ArrayList<Integer> e = new ArrayList<Integer>();
		try {
			 
            FileOutputStream fileOut = new FileOutputStream("src\\Tmp\\Objets");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(e);
            objectOut.close();
            System.out.println("The Object  was succesfully written to a file");
 
        } catch (Exception ex) {
            ex.printStackTrace();
        }


	}

}
