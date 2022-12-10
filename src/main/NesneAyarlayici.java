package main;

import varlik.NPC_YasliAdam;

public class NesneAyarlayici {
	
	OyunPaneli op;
	
	public NesneAyarlayici(OyunPaneli op) {
		this.op = op;
	}
	
	public void nesneAta() {
		
	}
	
	public void npcAta() {
		
		op.npcler[0] = new NPC_YasliAdam(op);
		op.npcler[0].dunyaX = op.mozaikBoyutu*21;
		op.npcler[0].dunyaY = op.mozaikBoyutu*21;
	}
}
