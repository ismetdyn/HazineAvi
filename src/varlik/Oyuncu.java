package varlik;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.OyunPaneli;
import main.TusYakalayici;

public class Oyuncu extends Varlik {
	
	TusYakalayici tusYakalayici;
	
	public final int ekranX;
	public final int ekranY;
	int sayac = 0;
	
	public Oyuncu(OyunPaneli op,TusYakalayici ty) {
		
		super(op);
		
		this.tusYakalayici = ty;
		
		ekranX = op.ekranGenisligi/2 -(op.mozaikBoyutu/2);
		ekranY = op.ekranYuksekligi/2 -(op.mozaikBoyutu/2);
		
		katiAlan = new Rectangle();
		katiAlan.x = 8;
		katiAlan.y = 16;
		
		katiAlanVarsayilanX = katiAlan.x;
		katiAlanVarsayilanY = katiAlan.y;
		
		katiAlan.width = 32;
		katiAlan.height = 32;
		
		
		varsayilanDegerleriAta();
		oyuncuGorselGetir();
	}
	
	public void varsayilanDegerleriAta() {
		dunyaX = op.mozaikBoyutu * 23;
		dunyaY = op.mozaikBoyutu * 21;
		hiz = 4;
		yon = "asagi";
	}
	
	public void oyuncuGorselGetir() {
		
		yukari1 = kurulum("/oyuncu/cocuk_yukari_1");
		yukari2 = kurulum("/oyuncu/cocuk_yukari_2");
		asagi1 = kurulum("/oyuncu/cocuk_asagi_1");
		asagi2 = kurulum("/oyuncu/cocuk_asagi_2");
		sol1 = kurulum("/oyuncu/cocuk_sol_1");
		sol2 = kurulum("/oyuncu/cocuk_sol_2");
		sag1 = kurulum("/oyuncu/cocuk_sag_1");
		sag2 = kurulum("/oyuncu/cocuk_sag_2");
	}
	
	public void guncelle() {
		
		if(tusYakalayici.yukariBasili == true ||
			tusYakalayici.asagiBasili == true ||
			tusYakalayici.solBasili == true ||
			tusYakalayici.sagBasili == true)
		{
			if(tusYakalayici.yukariBasili == true) {
				yon = "yukari";
			}
			if(tusYakalayici.asagiBasili == true) {
				yon = "asagi";
			}
			if(tusYakalayici.solBasili == true) {
				yon = "sol";
			}
			if(tusYakalayici.sagBasili == true) {
				yon = "sag";
			}
			
			// MOZAİK CARPİSMA KONTROL
			carpismaDurumu = false;
			op.carpismaDenetleyici.mozaikKontrol(this);
			
			// Nesne carpismaAlgilayici  Kontrol
			int nesneIndex = op.carpismaDenetleyici.nesneKontrol(this, true);
			nesneyiYerdenAl(nesneIndex);
			
			// NPC CARPİSMA KONTROL
			int npcIndex = op.carpismaDenetleyici.varlikKontrol(this, op.npcler);
			npcEtkilesim(npcIndex);
			
			// CARPİSMA YOKSA OYUNCU HAREKET EDEBİLİR
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
	}
	
	public void nesneyiYerdenAl(int i) {
		
		if (i != 999) {
			
			
		}
	}
	
	public void npcEtkilesim(int i){
		if (i != 999) {
			
			if(op.tusYakalayici.enterBasili) {
				op.oyunDurum = op.diyalogDurumu;
				op.npcler[i].konus();
			}
		}
		op.tusYakalayici.enterBasili = false;
	}
	
	public void ciz(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		g2.fillRect(x, y, oyunPaneli.kareBoyutu, oyunPaneli.kareBoyutu);
		
		BufferedImage gorsel = null;
		
		switch(yon) {
		case "yukari":
			if(spriteNo == 1) {
				gorsel = yukari1;
			}
			if(spriteNo == 2) {
				gorsel = yukari2;
			}
			break;
		case "asagi":
			if(spriteNo == 1) {
				gorsel = asagi1;
			}
			if(spriteNo == 2) {
				gorsel = asagi2;
			}
			break;
		case "sol":
			if(spriteNo == 1) {
				gorsel = sol1;
			}
			if(spriteNo == 2) {
				gorsel = sol2;
			}
			break;
		case "sag":
			if(spriteNo == 1) {
				gorsel = sag1;
			}
			if(spriteNo == 2) {
				gorsel = sag2;
			}
			break;
		}
		g2.drawImage(gorsel, ekranX, ekranY, null);
	}
}
