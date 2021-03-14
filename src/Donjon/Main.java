package Donjon;

import javax.swing.JFrame;
import java.util.Scanner;

public class Main {
	//***INITIALISATION***//
	public static Donjon scene;

	public static void main(String[] args) {
		
		//***CREATION DE LA FENETRE DE JEUX***//
		JFrame frame = new JFrame("the dungeon of hope");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quand on clique sur la croix rouge en haut de la frame on ferme le programme
		Scanner clavier = new Scanner(System.in);
        System.out.print("Saisir la taille de la map en largeur : ");
        int x = clavier.nextInt();
        System.out.print("Saisir la taille de la map en hauteur : ");
        int y = clavier.nextInt();
		frame.setSize(x,y);//résolution de la frame
		frame.setLocationRelativeTo(null);//Centrer la frame a l'écran
		frame.setResizable(false);//interdiction de redimensionner.
		frame.setAlwaysOnTop(true);//toujour au dessu des autre fenêtre
		//frame.getSize();
		//***INSTANCIATION DE L'OBJET SCENE***//
		scene = new Donjon(x,y);
		
		frame.setContentPane(scene);//association de la scene a la frame
		frame.setVisible(true);//On rend notre frame visible
		clavier.close();
	}

}
