package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Donjon extends JPanel {
	
	private ImageIcon solFond;
	private Image  sol;
	private ImageIcon piereFond;
	private Image  piere;
	private int xFond1;
	private ImageIcon murFond;
	private Image  mur;
	private ImageIcon persoFond;
	private Image  perso;
	
	private int persoX;
	private int persoy;
	
	

	
	
	//CONSTRUCTEUR
	public Donjon() {
		super();
		this.xFond1 = -50;
		this.persoX = 750;
		this.persoy = 400;
		
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		piereFond = new ImageIcon("src/images/piere.png");
		this.piere = this.piereFond.getImage();
		murFond = new ImageIcon("src/images/mur.png");
		this.mur = this.murFond.getImage();
		persoFond = new ImageIcon("src/images/perso0.png");
		this.perso = this.persoFond.getImage();
		
		Thread ecranRefresh = new Thread(new SceenRefresh() );
		ecranRefresh.start();
		
		
	}
	
	//METHODES
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D) g;
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.perso,this.persoX,this.persoy, null);
		for(int i = 0; i<900;i=i+50) {
			for(int j = 0; j<=450; j=j+50) {
				if((j== 400 && i==750)) {
				}
				else if(i==0||i==800||j==0||j==450) {
					g2.drawImage(mur, i, j, null);
				}
				else {
					g2.drawImage(piere, i, j, null);
				}
				
			}
		}
		
		
		
		
	}

	
	
	//GETTER & SETTER
	public int getPersoX() {
		return persoX;
	}

	public void setPersoX(int persoX) {
		this.persoX = persoX;
	}

	public int getPersoy() {
		return persoy;
	}

	public void setPersoy(int persoy) {
		this.persoy = persoy;
	}
	
	
	
	
	
	
	
	
}
