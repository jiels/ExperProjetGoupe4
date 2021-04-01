package Donjon;

import java.util.Scanner;

public class test {


	public static void main(String[] args) {
		Main map = new Main();
		String a = "QQQQ";
		System.out.println(map.info());
		map.getScene().ServerClavier(a);
		System.out.println(map.getScene().getJoueur().getX());
		
		
		


	}

}
