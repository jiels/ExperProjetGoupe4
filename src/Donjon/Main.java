package Donjon;

import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Main implements Runnable {
//***INITIALISATION***//
	private Donjon scene;

	public Main() {
		run();
		}
		public void run() {
		//***CREATION DE LA FENETRE DE JEUX***//
		JFrame frame = new JFrame("the dungeon of hope");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quand on clique sur la croix rouge en haut de la frame on ferme le programme
		Scanner clavier = new Scanner(System.in);
		int x=0;
		int y=0;
		while(x<10||x>20) {
			System.out.print("Saisir la taille de la map en largeur (entre 10 et 20) : ");
	        x = clavier.nextInt();
	        if(x<10)
	        	System.out.println("Erreur, taille trop petite");
	        else if(x>20)
	        	System.out.println("Erreur, taille trop grande");
		}
		while(y<10||y>14) {
			System.out.print("Saisir la taille de la map en hauteur (entre 10 et 14) : ");
	        y = clavier.nextInt();
	        if(y<10)
	        	System.out.println("Erreur, taille trop petite");
	        else if(y>14)
	        	System.out.println("Erreur, taille trop grande");
		}
        x=50*(x+2)+15;
        y=50*(y+2)+35;
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
		public String info() {
			String str = "Vie:"+this.getScene().getJoueur().getVie()+" Potion:"+this.getScene().getJoueur().getPotion();
			return str;
			
		}
	
	
	
	

	public Donjon getScene() {
		return scene;
	}

	



	
	

	
	
	

}
