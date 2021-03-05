package Donjon;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Clavier implements KeyListener  {

	@Override
	public void keyTyped(KeyEvent e) {
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			Main.scene.setX(1);
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			Main.scene.setX(-1);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Main.scene.setX(0);
		
	}

}
