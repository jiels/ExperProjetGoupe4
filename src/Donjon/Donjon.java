package Donjon;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Donjon extends JPanel {
	
	private ImageIcon solFond;
	private Image  sol;
	private int xFond1;
	/*private ImageIcon piereFond;
	private Image  piere;
	private ImageIcon murFond;
	private Image  mur;*/
	private ImageIcon persoFond;
	private Image  perso;
	
	private int persoX;
	private int x;
	private int persoY;
	private int y;
	
	

	
	
	//***CONSTRUCTEUR***//
	public Donjon() {
		super();
		this.xFond1 = -50;
		this.persoX = 750;
		this.persoY = 400;
		
		solFond = new ImageIcon("src/images/sol.jpg");
		this.sol = this.solFond.getImage();
		/*piereFond = new ImageIcon("src/images/piere.png");
		this.piere = this.piereFond.getImage();
		murFond = new ImageIcon("src/images/mur.png");
		this.mur = this.murFond.getImage();*/
		persoFond = new ImageIcon("src/images/perso0.png");
		this.perso = this.persoFond.getImage();
		
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new Clavier());
		
		Thread ecranRefresh = new Thread(new SceenRefresh() );
		ecranRefresh.start();
		
		
	}
	
	//***METHODES***//
	public void deplacementPersoX() {
		if(this.persoX!=750||this.persoX!=50) {
			this.persoX = this.persoX + this.x;
		}
	}
	public void deplacementPersY() {
		if(this.persoY!=100||this.persoY!=400) {
			this.persoY = this.persoY + this.y;
		}
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics g2 = (Graphics2D) g;
		
		this.deplacementPersoX();
		this.deplacementPersY();
		
		g2.drawImage(this.sol, this.xFond1, 0, null);//dessin de l'image de fond
		g2.drawImage(this.perso,this.persoX,this.persoY, null);//posistion du personnage
		for(int i = 0; i<900;i=i+50) {
			for(int j = 0; j<=450; j=j+50) {
				if(j== this.persoY -(this.persoY%50) && i==this.persoX-(this.persoX%50)) {
				}
				/*else if(i==0||i==800||j==0||j==450) {
					g2.drawImage(mur, i, j, null);
				}
				else {
					g2.drawImage(piere, i, j, null);
				}*/
				
			}
		}
		
		
		
		
	}

	
	
	
	//***GETTER & SETTER***//
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	
	
	
	
	
	
	
	
}
