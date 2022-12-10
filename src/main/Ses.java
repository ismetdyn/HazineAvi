package main;


import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Ses {
	
	Clip klip;
	URL sesURL[] = new URL[30];
	
	public Ses(){
		
		sesURL[0] = getClass().getResource("/ses/HazineAvi.wav");
		sesURL[1] = getClass().getResource("/ses/para.wav");
		sesURL[2] = getClass().getResource("/ses/gücArtisi.wav");
		sesURL[3] = getClass().getResource("/ses/kilidAc.wav");
		sesURL[4] = getClass().getResource("/ses/trombon.wav");
	}
	
	public void dosyaAta(int i) {
		try {
			AudioInputStream sga = AudioSystem.getAudioInputStream(sesURL[i]);
			klip = AudioSystem.getClip();
			klip.open(sga);
		}
		catch(Exception e) {
			
		}
	}
	
	public void oynat() {
		klip.start();
	}
	
	public void dongu() {
		klip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void durdur() {
		klip.stop();
	}
}
