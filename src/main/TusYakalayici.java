package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TusYakalayici implements KeyListener{
	
	OyunPaneli op;
	public boolean yukariBasili, asagiBasili, solBasili, sagBasili, enterBasili;
	// DEBUG
	boolean cizimSuresiKontrolEt = false;
	
	
	public TusYakalayici(OyunPaneli op) {
		this.op = op;
	}
	
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		
		int kod = e.getKeyCode();
		
		// OYNAMA DURUMU
		if(op.oyunDurum == op.oynamaDurumu) {
			
			if (kod == KeyEvent.VK_W) yukariBasili = true;
			
			if (kod == KeyEvent.VK_S) asagiBasili = true;
			
			if (kod == KeyEvent.VK_A) solBasili = true;
			
			if (kod == KeyEvent.VK_D) sagBasili = true;
			
			if (kod == KeyEvent.VK_P) {
				op.oyunDurum = op.durdurmaDurumu;
			}
			
			if(kod == KeyEvent.VK_ENTER) {
				enterBasili = true;
			}
			
			// DEBUG
			if (kod == KeyEvent.VK_T) {
				if(cizimSuresiKontrolEt == false) {
					cizimSuresiKontrolEt = true;
				}
				else if(cizimSuresiKontrolEt == true) {
					cizimSuresiKontrolEt = false;
				}
			}
		}
		
		// DURDURMA DURUMU
		else if(op.oyunDurum == op.durdurmaDurumu) {
			if(kod == KeyEvent.VK_P) {
				op.oyunDurum = op.oynamaDurumu;
			}
		}
		
		// DİYALOG DURUMU
		else if(op.oyunDurum == op.diyalogDurumu) {
			if(kod == KeyEvent.VK_ENTER) {
				op.oyunDurum = op.oynamaDurumu;
			}
		}
		
	}

	public void keyReleased(KeyEvent e) {
		
		int kod = e.getKeyCode();
		
		if (kod == KeyEvent.VK_W) {
			yukariBasili = false;
		}
		
		if (kod == KeyEvent.VK_S) {
			asagiBasili = false;
		}
		
		if (kod == KeyEvent.VK_A) {
			solBasili = false;
		}
		
		if (kod == KeyEvent.VK_D) {
			sagBasili = false;
		}
	}
	
}
