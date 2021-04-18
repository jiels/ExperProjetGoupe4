package Donjon;

import java.io.Serializable;

public class Position implements Serializable,Comparable<Position>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6298774822419706891L;
	private int x;
	private int y;

	public Position(int x , int y) {
		this.x=x;
		this.y=y;
	}

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
	@Override
	public String toString() {
		return "x"+getX()+" "+getY();
	}

	@Override
	public int compareTo(Position o) {
		if(this.getX()==o.getX()&&this.getY()==o.getY()) {
			return 0;
		}
		return -1;
	}

	
	
	
	
	

}
