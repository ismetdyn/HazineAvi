package nesne;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Hizmetler;
import main.OyunPaneli;

public class UstNesne {
	public BufferedImage gorsel;
	public String isim; 
	public boolean carpismaAlgilayici = false;
	public int  dunyaX, dunyaY;
	public Rectangle katiAlan = new Rectangle(0, 0, 48, 48);
	public int katiAlanVarsayilanX = 0;
	public int katiAlanVarsayilanY = 0;
	Hizmetler hizmet = new Hizmetler();
	
	public void ciz(Graphics2D g2, OyunPaneli op) {
		
		int ekranX = dunyaX - op.oyuncu.dunyaX + op.oyuncu.ekranX;
		int ekranY = dunyaY - op.oyuncu.dunyaY + op.oyuncu.ekranY;
		
		if(dunyaX + op.mozaikBoyutu > op.oyuncu.dunyaX - op.oyuncu.ekranX &&
		   dunyaX - op.mozaikBoyutu < op.oyuncu.dunyaX + op.oyuncu.ekranX &&
		   dunyaY + op.mozaikBoyutu > op.oyuncu.dunyaY - op.oyuncu.ekranY &&
		   dunyaY - op.mozaikBoyutu < op.oyuncu.dunyaY + op.oyuncu.ekranY)
		{
			g2.drawImage( gorsel, ekranX, ekranY, op.mozaikBoyutu, op.mozaikBoyutu, null);
		}
	}
}
