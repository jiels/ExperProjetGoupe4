package Donjon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener  {

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {//Doit
			Main.scene.setX(1);
			Main.scene.joueur.setMarche(true);
			Main.scene.joueur.setVersDroite(true);
			Main.scene.joueur.setVersGauche(false);
			Main.scene.joueur.setVersB(false);
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {//Gauche
			Main.scene.setX(-1);
			Main.scene.joueur.setMarche(true);
			Main.scene.joueur.setVersGauche(true);
			Main.scene.joueur.setVersDroite(false);
			Main.scene.joueur.setVersB(false);
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {//Haut
			Main.scene.setY(1);
			Main.scene.joueur.setVersDroite(false);
			Main.scene.joueur.setVersGauche(false);
			Main.scene.joueur.setVersB(true);
			Main.scene.joueur.setVersH(false);
			Main.scene.joueur.setMarche(true);
			
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {//Bas
			Main.scene.setY(-1);
			Main.scene.joueur.setVersDroite(false);
			Main.scene.joueur.setVersGauche(false);
			Main.scene.joueur.setVersH(true);
			Main.scene.joueur.setMarche(true);
			Main.scene.joueur.setVersB(false);
			
		}
		if(e.getKeyCode()==KeyEvent.VK_V) {
			Main.scene.joueur.usePotion();
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Main.scene.setX(0);
		Main.scene.setY(0);
		Main.scene.joueur.setMarche(false);
		
	}

}
