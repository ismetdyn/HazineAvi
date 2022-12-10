package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mozaik.MozaikYoneticisi;
import nesne.UstNesne;
import varlik.Oyuncu;
import varlik.Varlik;

public class OyunPaneli extends JPanel implements Runnable{
	
	// EKRAN AYARLARI
	final int orjinalMozaikBoyutu = 16; //16x16 pixel
	final int olcek = 3;
	
	public final int mozaikBoyutu = orjinalMozaikBoyutu * olcek; // 48x48 pixel
	public final int maxEkranKolon = 16;
	public final int maxEkranSatir = 12;
	public final int ekranGenisligi = mozaikBoyutu * maxEkranKolon; // 768 pixels
	public final int ekranYuksekligi = mozaikBoyutu * maxEkranSatir; // 576 pixels
	
	// DUNYA AYARLARI
	public final int maxDunyaKolon = 50;
	public final int maxDunyaSatir = 50;
	
	// FPS
	int FPS = 60;
	
	
	// SİSTEM
	MozaikYoneticisi mozaikYonetici = new MozaikYoneticisi(this);
	public TusYakalayici tusYakalayici = new TusYakalayici(this);
	Ses muzik = new Ses();
	Ses sesEfekt = new Ses();
	public CarpismaDenetleyici carpismaDenetleyici = new CarpismaDenetleyici(this);
	public NesneAyarlayici nesneAyarlayici = new NesneAyarlayici(this);
	public UI ui = new UI(this);
	Thread oyunIsParcacigi;
	
	// VARLIK VE NESNELER
	public Oyuncu oyuncu = new Oyuncu(this, tusYakalayici);
	public UstNesne nesneler[] = new UstNesne[10];
	public Varlik npcler[] = new Varlik[10];	
	
	// OYUN DURUMU
	public int oyunDurum; 
	public final int oynamaDurumu = 1;
	public final int durdurmaDurumu = 2;
	public final int diyalogDurumu = 3;
	
	public OyunPaneli() {
		
		this.setPreferredSize(new Dimension(ekranGenisligi, ekranYuksekligi));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(tusYakalayici);
		this.setFocusable(true);
	}
	
	public void oyunAyar() {
		nesneAyarlayici.nesneAta();
		nesneAyarlayici.npcAta();
		muzikCal(0);
		//muzikDurdur();
		oyunDurum = oynamaDurumu;
	}
	
	public void oyunIsParcacigiBaslat() {
		oyunIsParcacigi = new Thread(this);
		oyunIsParcacigi.start();
	}
	
	
	public void run() {
		double cizimAraligi = 1000000000/FPS;
		double delta = 0;
		double sonZaman = System.nanoTime();
		long simdikiZaman;
		long sureOlcer = 0;
		long cizimSayaci = 0; 
		
		while(oyunIsParcacigi != null) {
			
			simdikiZaman = System.nanoTime();
			
			delta += (simdikiZaman - sonZaman) / cizimAraligi;
			sureOlcer += (simdikiZaman - sonZaman);
			sonZaman = simdikiZaman;
			
			if (delta > 1) {
				guncelle();
				repaint();
				delta--;
				cizimSayaci++;
			}
			if(sureOlcer > 1000000000) {
				System.out.println("FPS:" + cizimSayaci);
				cizimSayaci = 0;
				sureOlcer = 0;
			}
		}
	}
	
	public void guncelle() {
		if(oyunDurum == oynamaDurumu) {
			// OYUNCU
			oyuncu.guncelle();
			
			// NPC
			for(int i = 0; i < npcler.length; i++) {
				if(npcler[i] != null) {
					npcler[i].guncelle();
				}
			}
		}
		if(oyunDurum == durdurmaDurumu) {
			// nothing
			
		}
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		// DEBUG
		long cizimBaslangic = 0;
		if(tusYakalayici.cizimSuresiKontrolEt == true) {
			cizimBaslangic = System.nanoTime();
		}
		
		
		// KAROLAR
		mozaikYonetici.ciz(g2);
		
		// NESNELER
		for(int i = 0; i < nesneler.length; i++) {
			if (nesneler[i] != null) nesneler[i].ciz(g2, this);
		}
		
		
		// NPC
		for(int i = 0; i < npcler.length; i++) {
			if(npcler[i] != null) npcler[i].ciz(g2);
		}
		
		// OYUNCU
		oyuncu.ciz(g2);
		
		// UI
		ui.ciz(g2);
		
		// DEBUG
		if(tusYakalayici.cizimSuresiKontrolEt == true) {
			long cizimBitis = System.nanoTime();
			long cizimSuresi = cizimBitis - cizimBaslangic;
			g2.setColor(Color.white);
			g2.drawString("Cizim Suresi: " + cizimSuresi, 10, 400);
			System.out.println("Cizim Suresi: " + cizimSuresi);
		}
		
		g2.dispose();
	}
	
	public void muzikCal(int i) {
		
		muzik.dosyaAta(i);
		muzik.oynat();
		muzik.dongu();
	}
	
	public void muzikDurdur() {
		muzik.durdur();
	}
	
	public void sesEfektCal(int i) {
		sesEfekt.dosyaAta(i);
		sesEfekt.oynat();
	}

}
