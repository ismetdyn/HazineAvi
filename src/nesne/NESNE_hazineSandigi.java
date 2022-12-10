package nesne;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.OyunPaneli;

public class NESNE_hazineSandigi extends UstNesne{
	
	OyunPaneli op;
	
	public NESNE_hazineSandigi(OyunPaneli op) {
		isim = "hazineSandigi";
		try {
			gorsel = ImageIO.read(getClass().getResourceAsStream("/nesneler/hazineSandigi.png"));
			hizmet.gorselOlceklendir(gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
