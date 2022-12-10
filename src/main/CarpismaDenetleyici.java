package main;

import varlik.Varlik;

public class CarpismaDenetleyici {

	OyunPaneli op;
	
	public CarpismaDenetleyici(OyunPaneli op) {
		this.op = op;
	}
	
	public void mozaikKontrol(Varlik varlik){
		int varlikSolDunyaX = varlik.dunyaX + varlik.katiAlan.x;
		int varlikSagDunyaX = varlik.dunyaX + varlik.katiAlan.x + varlik.katiAlan.width;
		int varlikYukariDunyaY  = varlik.dunyaY + varlik.katiAlan.y;
		int varlikAsagiDunyaY = varlik.dunyaY + varlik.katiAlan.y + varlik.katiAlan.height;
		
		int varlikSolKolon = varlikSolDunyaX/op.mozaikBoyutu;
		int varlikSagKolon = varlikSagDunyaX/op.mozaikBoyutu;
		int varlikYukariSatir  = varlikYukariDunyaY/op.mozaikBoyutu;
		int varlikAsagiSatir = varlikAsagiDunyaY/op.mozaikBoyutu;
		
		int mozaikNum1, mozaikNum2;
		
		switch(varlik.yon){
		case "yukari":
			varlikYukariSatir = (varlikYukariDunyaY - varlik.hiz)/op.mozaikBoyutu;
			mozaikNum1 = op.mozaikYonetici.haritaMozaikNum[varlikSolKolon][varlikYukariSatir];
			mozaikNum2 = op.mozaikYonetici.haritaMozaikNum[varlikSagKolon][varlikYukariSatir];
			if(op.mozaikYonetici.mozaik[mozaikNum1].carpismaAlgilayici == true ||
					op.mozaikYonetici.mozaik[mozaikNum2].carpismaAlgilayici == true) {
				varlik.carpismaDurumu = true;
			}
			break;
		case "asagi":
			varlikAsagiSatir = (varlikAsagiDunyaY + varlik.hiz)/op.mozaikBoyutu;
			mozaikNum1 = op.mozaikYonetici.haritaMozaikNum[varlikSolKolon][varlikAsagiSatir];
			mozaikNum2 = op.mozaikYonetici.haritaMozaikNum[varlikSagKolon][varlikAsagiSatir];
			if(op.mozaikYonetici.mozaik[mozaikNum1].carpismaAlgilayici == true ||
					op.mozaikYonetici.mozaik[mozaikNum2].carpismaAlgilayici == true) {
				varlik.carpismaDurumu = true;
			}
			break;
		case "sol":
			varlikSolKolon = (varlikSolDunyaX - varlik.hiz)/op.mozaikBoyutu;
			mozaikNum1 = op.mozaikYonetici.haritaMozaikNum[varlikSolKolon][varlikYukariSatir];
			mozaikNum2 = op.mozaikYonetici.haritaMozaikNum[varlikSolKolon][varlikAsagiSatir];
			if(op.mozaikYonetici.mozaik[mozaikNum1].carpismaAlgilayici == true ||
					op.mozaikYonetici.mozaik[mozaikNum2].carpismaAlgilayici == true) {
				varlik.carpismaDurumu = true;
			}
			break;
		case "sag":
			varlikSagKolon = (varlikSagDunyaX + varlik.hiz)/op.mozaikBoyutu;
			mozaikNum1 = op.mozaikYonetici.haritaMozaikNum[varlikSagKolon][varlikYukariSatir];
			mozaikNum2 = op.mozaikYonetici.haritaMozaikNum[varlikSagKolon][varlikAsagiSatir];
			if(op.mozaikYonetici.mozaik[mozaikNum1].carpismaAlgilayici == true ||
					op.mozaikYonetici.mozaik[mozaikNum2].carpismaAlgilayici == true) {
				varlik.carpismaDurumu = true;
			}
			break;
		}
	}
	
	public int nesneKontrol(Varlik varlik, boolean oyuncu) {
		
		int index = 999;
		
		for(int i = 0; i < op.nesneler.length; i++) {
			if(op.nesneler[i] != null) {
				
				// varlik kati alanlarına eriş
				varlik.katiAlan.x = varlik.dunyaX + varlik.katiAlan.x;
				varlik.katiAlan.y = varlik.dunyaY + varlik.katiAlan.y;
				
				// nesnenin kati alanlarina eriş
				op.nesneler[i].katiAlan.x = op.nesneler[i].dunyaX + op.nesneler[i].katiAlan.x;
				op.nesneler[i].katiAlan.y = op.nesneler[i].dunyaY + op.nesneler[i].katiAlan.y;
				
				switch (varlik.yon) {
				case "yukari":
					varlik.katiAlan.y -= varlik.hiz;
					if(varlik.katiAlan.intersects(op.nesneler[i].katiAlan)) {
						if (op.nesneler[i].carpismaAlgilayici == true) {
							varlik.carpismaDurumu = true;
						}
						if(oyuncu == true) {
							index = i;
						}
					}
					break;
				case "asagi":
					varlik.katiAlan.y += varlik.hiz;
					if(varlik.katiAlan.intersects(op.nesneler[i].katiAlan)) {
						if (op.nesneler[i].carpismaAlgilayici == true) {
							varlik.carpismaDurumu = true;
						}
						if(oyuncu == true) {
							index = i;
						}
					}
					break;
				case "sol":
					varlik.katiAlan.x -= varlik.hiz;
					if(varlik.katiAlan.intersects(op.nesneler[i].katiAlan)) {
						if (op.nesneler[i].carpismaAlgilayici == true) {
							varlik.carpismaDurumu = true;
						}
						if(oyuncu == true) {
							index = i;
						}
					}
					break;
				case "sag":
					varlik.katiAlan.x += varlik.hiz;
					if(varlik.katiAlan.intersects(op.nesneler[i].katiAlan)) {
						if (op.nesneler[i].carpismaAlgilayici == true) {
							varlik.carpismaDurumu = true;
						}
						if(oyuncu == true) {
							index = i;
						}
					}
					break;
				}
				varlik.katiAlan.x = varlik.katiAlanVarsayilanX;
				varlik.katiAlan.y = varlik.katiAlanVarsayilanY;
				op.nesneler[i].katiAlan.x = op.nesneler[i].katiAlanVarsayilanX;
				op.nesneler[i].katiAlan.y = op.nesneler[i].katiAlanVarsayilanY;
			}
		}
		
		return index; 		
	}
	
	
	// NPC VEYA CANAVAR 
	public int varlikKontrol(Varlik varlik, Varlik[] hedef) {
int index = 999;
		
		for(int i = 0; i < hedef.length; i++) {
			if(hedef[i] != null) {
				
				// varlik kati alanlarına eriş
				varlik.katiAlan.x = varlik.dunyaX + varlik.katiAlan.x;
				varlik.katiAlan.y = varlik.dunyaY + varlik.katiAlan.y;
				
				// nesnenin kati alanlarina eriş
				hedef[i].katiAlan.x = hedef[i].dunyaX + hedef[i].katiAlan.x;
				hedef[i].katiAlan.y = hedef[i].dunyaY + hedef[i].katiAlan.y;
				
				switch (varlik.yon) {
				case "yukari":
					varlik.katiAlan.y -= varlik.hiz;
					if(varlik.katiAlan.intersects(hedef[i].katiAlan)) {
							varlik.carpismaDurumu = true;
							index = i;
					}
					break;
				case "asagi":
					varlik.katiAlan.y += varlik.hiz;
					if(varlik.katiAlan.intersects(hedef[i].katiAlan)) {
							varlik.carpismaDurumu = true;
							index = i;
					}
					break;
				case "sol":
					varlik.katiAlan.x -= varlik.hiz;
					if(varlik.katiAlan.intersects(hedef[i].katiAlan)) {
							varlik.carpismaDurumu = true;
							index = i;
					}
					break;
				case "sag":
					varlik.katiAlan.x += varlik.hiz;
					if(varlik.katiAlan.intersects(hedef[i].katiAlan)) {
							varlik.carpismaDurumu = true;
							index = i;
					}
					break;
				}
				varlik.katiAlan.x = varlik.katiAlanVarsayilanX;
				varlik.katiAlan.y = varlik.katiAlanVarsayilanY;
				hedef[i].katiAlan.x = hedef[i].katiAlanVarsayilanX;
				hedef[i].katiAlan.y = hedef[i].katiAlanVarsayilanY;
			}
		}
		
		return index; 		
	}
	
	public void oyuncuKontrol(Varlik varlik) {
		// varlik kati alanlarına eriş
		varlik.katiAlan.x = varlik.dunyaX + varlik.katiAlan.x;
		varlik.katiAlan.y = varlik.dunyaY + varlik.katiAlan.y;
		
		// nesnenin kati alanlarina eriş
		op.oyuncu.katiAlan.x = op.oyuncu.dunyaX + op.oyuncu.katiAlan.x;
		op.oyuncu.katiAlan.y = op.oyuncu.dunyaY + op.oyuncu.katiAlan.y;
		
		switch (varlik.yon) {
		case "yukari":
			varlik.katiAlan.y -= varlik.hiz;
			if(varlik.katiAlan.intersects(op.oyuncu.katiAlan)) {
					varlik.carpismaDurumu = true;
			}
			break;
		case "asagi":
			varlik.katiAlan.y += varlik.hiz;
			if(varlik.katiAlan.intersects(op.oyuncu.katiAlan)) {
					varlik.carpismaDurumu = true;
			}
			break;
		case "sol":
			varlik.katiAlan.x -= varlik.hiz;
			if(varlik.katiAlan.intersects(op.oyuncu.katiAlan)) {
					varlik.carpismaDurumu = true;
			}
			break;
		case "sag":
			varlik.katiAlan.x += varlik.hiz;
			if(varlik.katiAlan.intersects(op.oyuncu.katiAlan)) {
					varlik.carpismaDurumu = true;
			}
			break;
		}
		varlik.katiAlan.x = varlik.katiAlanVarsayilanX;
		varlik.katiAlan.y = varlik.katiAlanVarsayilanY;
		op.oyuncu.katiAlan.x = op.oyuncu.katiAlanVarsayilanX;
		op.oyuncu.katiAlan.y = op.oyuncu.katiAlanVarsayilanY;
	}
}
