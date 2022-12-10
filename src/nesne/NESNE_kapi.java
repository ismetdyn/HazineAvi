package nesne;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.OyunPaneli;

public class NESNE_kapi extends UstNesne{
	
	OyunPaneli op;
	
	public NESNE_kapi(OyunPaneli op) {
		isim = "kapi";
		try {
			gorsel = ImageIO.read(getClass().getResourceAsStream("/nesneler/kapi.png"));
			hizmet.gorselOlceklendir(gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		carpismaAlgilayici = true;
	}
}
