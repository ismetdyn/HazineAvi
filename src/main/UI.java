package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class UI {
	
	OyunPaneli op;
	Graphics2D g2;
	Font arial_40, arial_80K;
	public boolean mesajDurumu = false;
	public String mesaj = "";
	int mesajSayaci = 0;
	public boolean oyunBitti = false;
	public String guncelDiyalog = "";
	
	public UI(OyunPaneli op) {
		this.op = op;
		
		arial_40 = new Font("Cambria", Font.PLAIN, 40);
		arial_80K = new Font("Arial", Font.BOLD, 80);
	}
	
	
	public void mesajGoster(String text) {
		
		mesaj = text;
		mesajDurumu = true;
	}
	
	public void ciz(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.orange);
		
		// ONAMA DURUMU
		if (op.oyunDurum == op.oynamaDurumu) {
			// to do
		}
		
		// DURDURMA DURUMU
		if (op.oyunDurum == op.durdurmaDurumu) {
			durdurmaEkraniCiz();
		}
		
		// DİYALOG DURUMU
		if (op.oyunDurum == op.diyalogDurumu) {
			diyalogEkraniCiz();
		}
	}
	
	public void durdurmaEkraniCiz() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String metin = "DURDURULDU";
		int x = metinMerkezXKoordinatiniGetir(metin);
		int y = op.ekranYuksekligi/2;
		g2.drawString(metin, x, y);
	}
	
	public void diyalogEkraniCiz() {
			
		// PENCERE
		int x = op.mozaikBoyutu*2;
		int y = op.mozaikBoyutu/2;
		int genislik = op.ekranGenisligi - (op.mozaikBoyutu*4);
		int yukseklik = op.mozaikBoyutu*4;
		
		altPencereCiz(x, y, genislik, yukseklik);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
		x  += op.mozaikBoyutu;
		y += op.mozaikBoyutu;
		
		for(String satir : guncelDiyalog.split("\n")) {
			g2.drawString(satir, x, y);
			y += 40;
		}
	}
	
	public void altPencereCiz(int x, int y, int genislik, int yukseklik) {
		
		Color renk = new Color(0, 0, 0, 210);
		g2.setColor(renk);
		g2.fillRoundRect(x, y, genislik, yukseklik, 35, 35);
		
		renk = new Color(255, 255, 255);
		g2.setColor(renk);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x + 5, y + 5, genislik - 10, yukseklik - 10, 25, 25);
	}
	
	public int metinMerkezXKoordinatiniGetir(String metin) {
		
		int uzunluk = (int)g2.getFontMetrics().getStringBounds(metin, g2).getWidth();
		int x = op.ekranGenisligi/2 - uzunluk/2;
		return x;
	}
}
