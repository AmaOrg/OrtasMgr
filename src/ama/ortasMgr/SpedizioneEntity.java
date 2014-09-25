package ama.ortasMgr;

public class SpedizioneEntity {
	private SpedizioneModel _m;
	
	public SpedizioneEntity(SpedizioneModel m) {
		this._m = m;
	}
	
	public SpedizioneModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setLdv(String value) {
		this._m.ldv = value;
	}
	public String getLdv() {
		return this._m.ldv;
	}
	
	public void setData(GregorianCalendar value) {
		this._m.data = value;
	}
	public GregorianCalendar getData() {
		return this._m.data;
	}
	
	public void setNote(String value) {
		this._m.note = value;
	}
	public String getNote() {
		return this._m.note;
	}
}