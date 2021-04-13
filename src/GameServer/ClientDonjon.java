package GameServer;

import javax.swing.JFrame;

import Donjon.Donjon;

public class ClientDonjon implements Runnable {
	private static int rx;
	private static int ry;
	private static String id;
	private static Donjon scene;

	public ClientDonjon(String id,int rx,int ry) {
		this.id=id;
		this.rx=rx;
		this.ry=ry;
		this.scene = new Donjon(rx, ry);
	}

 	

 public void run(){
	 JFrame frame = new JFrame(id+" the dungeon of hope");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setSize(rx,ry);
	 frame.setLocationRelativeTo(null);
	 frame.setResizable(false);
	 frame.setAlwaysOnTop(true);
	 frame.setVisible(true);
	 frame.setContentPane(scene);
	 }



public static int getRx() {
	return rx;
}



public static void setRx(int rx) {
	ClientDonjon.rx = rx;
}



public static int getRy() {
	return ry;
}



public static void setRy(int ry) {
	ClientDonjon.ry = ry;
}



public Donjon getScene() {
	return scene;
}



public static void setScene(Donjon scene) {
	ClientDonjon.scene = scene;
}

	

}
