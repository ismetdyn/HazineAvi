package varlik;

import java.util.Random;

import main.OyunPaneli;

public class NPC_YasliAdam extends Varlik {
	
	public NPC_YasliAdam(OyunPaneli op) {
		super(op);
		
		yon = "asagi";
		hiz = 1;
		
		gorselGetir();
		diyalogAta();
	}
	
	public void gorselGetir() {
		
		yukari1 = kurulum("/npc/yasliAdam_yukari_1");
		yukari2 = kurulum("/npc/yasliAdam_yukari_2");
		asagi1 = kurulum("/npc/yasliAdam_asagi_1");
		asagi2 = kurulum("/npc/yasliAdam_asagi_2");
		sol1 = kurulum("/npc/yasliAdam_sol_1");
		sol2 = kurulum("/npc/yasliAdam_sol_2");
		sag1 = kurulum("/npc/yasliAdam_sag_1");
		sag2 = kurulum("/npc/yasliAdam_sag_2");
	}
	
	public void diyalogAta() {
		
		diyaloglar[0] = "Merhaba evlat, nasılsın?";
		diyaloglar[1] = "Teşekkürler, iyiyim.\nBu adaya hazineyi bulmaya mı geldin?";
		diyaloglar[2] = "Eskiden harika bir büyücüydüm.\nAma şimdi maceraya atılmak için\n biraz yaşlıyım.?";
		diyaloglar[3] = "Döndüğünde ailene selam söyle.\nNinene de selam söyle?";
	}
	
	public void hareketKat() {
		
		harketEngellemeSayaci++;
		
		if (harketEngellemeSayaci == 120) {
			Random rastgele = new Random();
			int i = rastgele.nextInt(100) + 1;
			
			if (i <= 25) yon = "yukari";
			
			if (i > 25 && i <= 50) yon = "asagi";
			
			if (i > 50 && i <= 75) yon = "sol";
			
			if (i > 75 && i <= 100) yon = "sag";
			
			harketEngellemeSayaci = 0;
		}	
	}
	
	public void konus() {
		
		// Bu karaktere özgü şeyleri yap
		
		super.konus();
	}
}
