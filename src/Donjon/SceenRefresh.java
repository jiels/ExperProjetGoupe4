package Donjon;

public class SceenRefresh implements Runnable {
	/*Cette permet de rafraichir l'�cran de jeux toute les 3 millisecond
	 * c'est a dire de red�ssin� notre �cran de jeux toute les 3 millisecond
	 * pour cr�� un effet de mouvement
	 */
	private final int refrehPause = 3; //Le temps d'attente entre deux rafraichisement

	@Override
	public void run() {
		while(true) {
			Main.scene.repaint();
			try {
				Thread.sleep(refrehPause);
			} catch (InterruptedException e) {}
		}
		
	}

}
