package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame pencere = new JFrame();
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setResizable(false);
		pencere.setTitle("Hazine Avı");
		
		OyunPaneli oyunPaneli = new OyunPaneli();
		pencere.add(oyunPaneli);
		
		pencere.pack();
		
		pencere.setLocationRelativeTo(null);
		pencere.setVisible(true);
		
		oyunPaneli.oyunAyar();
		oyunPaneli.oyunIsParcacigiBaslat();
	}

}
