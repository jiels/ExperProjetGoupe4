package Donjon;

import javax.swing.JFrame;


public class Main{
//***INITIALISATION***//
	private Donjon scene;
	private int x;
	private int y;

	public Main(int x, int y) {
		this.x=x;
		this.y=y;
		run();
		}
		public void run() {
		//***CREATION DE LA FENETRE DE JEUX***//
		JFrame frame = new JFrame("the dungeon of hope");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//quand on clique sur la croix rouge en haut de la frame on ferme le programme
		frame.setSize(x,y);//résolution de la frame
		frame.setLocationRelativeTo(null);//Centrer la frame a l'écran
		frame.setResizable(false);//interdiction de redimensionner.
		frame.setAlwaysOnTop(true);//toujour au dessu des autre fenêtre
		//frame.getSize();
		//***INSTANCIATION DE L'OBJET SCENE***//
		scene = new Donjon(x,y);
		frame.setContentPane(scene);//association de la scene a la frame
		frame.setVisible(true);//On rend notre frame visible
		//clavier.close();
	}
		
		
		
		
		
	public String info() {
		String str = "Vie:"+this.scene.getJoueur().getVie()+" Potion:"+this.scene.getJoueur().getPotion();
		return str;
			
		}
	
	
	
	public Donjon getScene() {
		return scene;
	}

	



	
	

	
	
	

}
