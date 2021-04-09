package Donjon;

public class PositionJoueur extends Position {
	private String id;

	public PositionJoueur(String id ,int x,int y) {
		super(x,y);
		this.id=id;
	}

	public String getId() {
		return id;
	}
	
	
	

}
