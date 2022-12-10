package nesne;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.OyunPaneli;

public class NESNE_bot extends UstNesne{
	
	OyunPaneli op;
	
	public NESNE_bot(OyunPaneli op) {
		isim = "bot";
		try {
			gorsel = ImageIO.read(getClass().getResourceAsStream("/nesneler/bot.png"));
			hizmet.gorselOlceklendir(gorsel, op.mozaikBoyutu, op.mozaikBoyutu);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
