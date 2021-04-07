package GameServer;

import javax.swing.JFrame;

import Donjon.Donjon;

public class ClientDonjon{
	private static int rx;
	private static int ry;
	private static String id;
	private static Donjon scene;

	public ClientDonjon(String id,int rx,int ry) {
		this.id=id;
		this.rx=rx;
		this.ry=ry;
	}

 	
	
public static void main(String[] args) {
	JFrame frame = new JFrame(id+" the dungeon of hope");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(rx,ry);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);
	frame.setAlwaysOnTop(true);
	frame.setVisible(true);
	
}
}
