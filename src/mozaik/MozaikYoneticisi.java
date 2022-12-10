package mozaik;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Hizmetler;
import main.OyunPaneli;

public class MozaikYoneticisi {

	OyunPaneli op;
	public Mozaik[] mozaik;
	public int haritaMozaikNum[][];
	
	public MozaikYoneticisi(OyunPaneli op) {
		this.op = op;
		
		mozaik = new Mozaik[50];
		haritaMozaikNum = new int[op.maxDunyaKolon][op.maxDunyaSatir];
		
		mozaikGorselGetir();
		haritaYukle("/haritalar/dunyaV2.txt");
	}
	
	public void mozaikGorselGetir() {
		
		// GEREKSİZ -ÖNLEM AMAÇLI 
		kurulum(0, "cimen00", false);
		kurulum(1, "cimen00", false);
		kurulum(2, "cimen00", false);
		kurulum(3, "cimen00", false);
		kurulum(4, "cimen00", false);
		kurulum(5, "cimen00", false);
		kurulum(6, "cimen00", false);
		kurulum(7, "cimen00", false);
		kurulum(8, "cimen00", false);
		kurulum(9, "cimen00", false);
		// GEREKSİZ -ÖNLEM AMAÇLI
		
		kurulum(10, "cimen00", false);
		kurulum(11, "cimen01", false);
		kurulum(12, "su00", true);
		kurulum(13, "su01", true);
		kurulum(14, "su02", true);
		kurulum(15, "su03", true);
		kurulum(16, "su04", true);
		kurulum(17, "su05", true);
		kurulum(18, "su06", true);
		kurulum(19, "su07", true);
		kurulum(20, "su08", true);
		kurulum(21, "su09", true);
		kurulum(22, "su10", true);
		kurulum(23, "su11", true);
		kurulum(24, "su12", true);
		kurulum(25, "su13", true);
		kurulum(26, "yol00", false);
		kurulum(27, "yol01", false);
		kurulum(28, "yol02", false);
		kurulum(29, "yol03", false);
		kurulum(30, "yol04", false);
		kurulum(31, "yol05", false);
		kurulum(32, "yol06", false);
		kurulum(33, "yol07", false);
		kurulum(34, "yol08", false);
		kurulum(35, "yol09", false);
		kurulum(36, "yol10", false);
		kurulum(37, "yol11", false);
		kurulum(38, "yol12", false);
		kurulum(39, "toprak", false);
		kurulum(40, "duvar", true);
		kurulum(41, "agac", true);
	}
	
	public void kurulum(int index, String gorselIsmi, boolean carpismaAlgilayici) {
		
		Hizmetler hizmet = new Hizmetler();
		
		try {
			mozaik[index] = new Mozaik();
			mozaik[index].gorsel = ImageIO.read( getClass().getResource("/mozaikler/" + gorselIsmi+".png"));
			mozaik[index].gorsel = hizmet.gorselOlceklendir(mozaik[index].gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
			mozaik[index].carpismaAlgilayici = carpismaAlgilayici;
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void haritaYukle(String dosyaYolu) {
		try {
			
			InputStream is = getClass().getResourceAsStream(dosyaYolu);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int kolonNo = 0;
			int satirNo = 0;
			
			while(kolonNo < op.maxDunyaKolon && satirNo < op.maxDunyaSatir) {
				String satir = br.readLine();
				
				while(kolonNo < op.maxDunyaKolon) {
					String sayilar[] = satir.split(" ");
					
					int sayi = Integer.parseInt(sayilar[kolonNo]);
					
					haritaMozaikNum[kolonNo][satirNo] = sayi;
					kolonNo++;
				}
				if(kolonNo == op.maxDunyaKolon){
					kolonNo = 0;
					satirNo++;
				}
			}
			br.close();
		}
		catch (Exception e){
			
		}
	}
	
	public void ciz(Graphics2D g2) {
		int dunyaKolon = 0;
		int dunyaSatir = 0;
		
		while(dunyaKolon < op.maxDunyaKolon && dunyaSatir < op.maxDunyaSatir) {
			
			int mozaikNum = haritaMozaikNum [dunyaKolon][dunyaSatir];
			
			int dunyaX = dunyaKolon * op.mozaikBoyutu;
			int dunyaY = dunyaSatir * op.mozaikBoyutu;
			int ekranX = dunyaX - op.oyuncu.dunyaX + op.oyuncu.ekranX;
			int ekranY = dunyaY - op.oyuncu.dunyaY + op.oyuncu.ekranY;
			
			if(dunyaX + op.mozaikBoyutu > op.oyuncu.dunyaX - op.oyuncu.ekranX &&
			   dunyaX - op.mozaikBoyutu < op.oyuncu.dunyaX + op.oyuncu.ekranX &&
			   dunyaY + op.mozaikBoyutu > op.oyuncu.dunyaY - op.oyuncu.ekranY &&
			   dunyaY - op.mozaikBoyutu < op.oyuncu.dunyaY + op.oyuncu.ekranY)
			{
				g2.drawImage( mozaik[mozaikNum ].gorsel, ekranX, ekranY, null);
			}
			
			dunyaKolon++;
			if (dunyaKolon == op.maxDunyaKolon) {
				dunyaKolon = 0;

				dunyaSatir++;
				
			}
		}
	}
}
