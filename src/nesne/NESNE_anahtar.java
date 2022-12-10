package nesne;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.OyunPaneli;

public class NESNE_anahtar extends UstNesne {
	
	OyunPaneli op;
	
	public NESNE_anahtar(OyunPaneli op) {
		isim = "anahtar";
		try {
			gorsel = ImageIO.read(getClass().getResourceAsStream("/nesneler/anahtar.png"));
			hizmet.gorselOlceklendir(gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
