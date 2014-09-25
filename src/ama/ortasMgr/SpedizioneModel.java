package ama.ortasMgr;

import java.util.GregorianCalendar;

public class SpedizioneModel {
	public String id;
	public String ldv; // lettera di vettura
	public GregorianCalendar data;
	public String note;
	
	public SpedizioneEntity getEntity() {
		SpedizioneEntity e = new SpedizioneEntity(this);
		return e;
	}
}