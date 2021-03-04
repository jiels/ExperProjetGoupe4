package Donjon;

import javax.swing.JFrame;

public class Main {
	//INITIALISATION
	public static Donjon scene;

	public static void main(String[] args) {
		
		// Cr�ation de la fenetre de jeux 
		JFrame frame = new JFrame("the dungeon of hope");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quand on clique sur la croix rouge en haut de la frame on ferme le programme
		frame.setSize(865,540);//r�solution de la frame
		frame.setLocationRelativeTo(null);//Centrer la frame a l'�cran
		frame.setResizable(false);//interdiction de redimensionner.
		frame.setAlwaysOnTop(true);//toujour au dessu des autre fen�tre
		
		//instance odjet scenne
		scene = new Donjon();
		
		frame.setContentPane(scene);//association de la scene a la frame
		frame.setVisible(true);//On rend notre frame visible
	}

}
