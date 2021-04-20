package Donjon;

public class SceneRefresh implements Runnable {
	/*Cette permet de rafraichir l'�cran de jeux toute les 3 millisecond
	 * c'est a dire de red�ssin� notre �cran de jeux toute les 3 millisecond
	 * pour cr�� un effet de mouvement
	 */
	private final int refrehPause = 3; //Le temps d'attente entre deux rafraichisement
	private Donjon sc;

	public SceneRefresh(Donjon donjon) {
		this.sc=donjon;
	}
	@Override
	public void run() {
		while(true) {
			this.getSc().repaint();
			try {
				Thread.sleep(refrehPause);
			} catch (InterruptedException e) {}
		}
		
	}
	public Donjon getSc() {
		return sc;
	}
	

}
