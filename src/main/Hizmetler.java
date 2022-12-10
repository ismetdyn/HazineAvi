package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Hizmetler {
	public BufferedImage gorselOlceklendir(
			BufferedImage orjinal, 
			int genislik, 
			int yukseklik) 
	{
		BufferedImage olceklenmisGorsel = new BufferedImage(genislik, yukseklik, orjinal.getType());
		Graphics2D g2 = olceklenmisGorsel.createGraphics();
		g2.drawImage(orjinal, 0, 0, genislik, yukseklik, null);
		g2.dispose();
		
		return olceklenmisGorsel;
	}
}
