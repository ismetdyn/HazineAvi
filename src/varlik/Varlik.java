package varlik;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Hizmetler;
import main.OyunPaneli;

public class Varlik {
	
	OyunPaneli op;
	public int dunyaX, dunyaY;
	public int hiz;
	public BufferedImage yukari1, yukari2, asagi1, asagi2, sol1, sol2, sag1, sag2;
	public String yon;
	public int spriteSayaci = 0;
	public int spriteNo = 1;
	public Rectangle katiAlan = new Rectangle(0, 0, 48, 48);
	public int katiAlanVarsayilanX, katiAlanVarsayilanY;
	public boolean carpismaDurumu = false;
	public int harketEngellemeSayaci = 0;
	String diyaloglar[] = new String[20];
	public int diyalogIndex = 0;
	
	public Varlik(OyunPaneli op) {
		this.op = op;
	}
	
	public void hareketKat() {}
	public void konus() {
		
		if(diyaloglar[diyalogIndex] == null) {
			diyalogIndex = 0;
		}
		
		switch(op.oyuncu.yon) {
		case "yukari": yon = "asagi"; break;
		case "asagi": yon = "yukari"; break;
		case "sol": yon = "sag"; break;
		case "sag": yon = "sol"; break;
		}
		
		op.ui.guncelDiyalog = diyaloglar[diyalogIndex];
		diyalogIndex++;
	}
	
	
	
	public void guncelle() {
		hareketKat();
		carpismaDurumu = false;
		op.carpismaDenetleyici.mozaikKontrol(this);
		op.carpismaDenetleyici.nesneKontrol(this, false);
		op.carpismaDenetleyici.oyuncuKontrol(this);
		
		// CARPIŞMA YOKSA HAREKET EDEBİLİR
		if(carpismaDurumu == false) {
			switch(yon) {
			case "yukari": dunyaY -= hiz; break;
			case "asagi": dunyaY += hiz; break;
			case "sol": dunyaX -= hiz; break;
			case "sag": dunyaX += hiz; break;
			}
		}
		
		spriteSayaci++;
		if(spriteSayaci > 10) {
			if (spriteNo == 1) {
				spriteNo = 2;
			}
			else if (spriteNo == 2) {
				spriteNo =1;
			}
			spriteSayaci = 0;
		}
	}
	
	public void ciz(Graphics2D g2) {
		
		BufferedImage gorsel = null;
		
		int ekranX = dunyaX - op.oyuncu.dunyaX + op.oyuncu.ekranX;
		int ekranY = dunyaY - op.oyuncu.dunyaY + op.oyuncu.ekranY;
		
		if(dunyaX + op.mozaikBoyutu > op.oyuncu.dunyaX - op.oyuncu.ekranX &&
		   dunyaX - op.mozaikBoyutu < op.oyuncu.dunyaX + op.oyuncu.ekranX &&
		   dunyaY + op.mozaikBoyutu > op.oyuncu.dunyaY - op.oyuncu.ekranY &&
		   dunyaY - op.mozaikBoyutu < op.oyuncu.dunyaY + op.oyuncu.ekranY)
		{
			
			switch(yon) {
			case "yukari":
				if(spriteNo == 1) gorsel = yukari1;
				
				if(spriteNo == 2) gorsel = yukari2;
				
				break;
			case "asagi":
				if(spriteNo == 1) gorsel = asagi1;
				
				if(spriteNo == 2) gorsel = asagi2;
				
				break;
			case "sol":
				if(spriteNo == 1) gorsel = sol1;
				
				if(spriteNo == 2) gorsel = sol2;
				
				break;
			case "sag":
				if(spriteNo == 1) gorsel = sag1;
				
				if(spriteNo == 2) gorsel = sag2;
				
				break;
			}
			
			g2.drawImage( gorsel, ekranX, ekranY, op.mozaikBoyutu, op.mozaikBoyutu, null);
		}
	}
	
	public BufferedImage kurulum(String gorselYolu) {
		Hizmetler hizmet = new Hizmetler();
		BufferedImage gorsel = null;
		
		try {
			gorsel = ImageIO.read(getClass().getResourceAsStream(gorselYolu + ".png"));
			gorsel = hizmet.gorselOlceklendir(gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return gorsel;
	}
}
