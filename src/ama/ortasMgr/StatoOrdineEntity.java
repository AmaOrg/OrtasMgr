package ama.ortasMgr;

public class StatoOrdineModelEntity {
	private StatoOrdineModel _m;
	
	public StatoOrdineModelEntity(StatoOrdineModelModel m) {
		this._m = m;
	}
	
	public StatoOrdineModelModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setCodice(String value) {
		this._m.codice = value;
	}
	public String getCodice() {
		return this._m.codice;
	}
	
	public void setLabel(String value) {
		this._m.label = value;
	}
	public String getLabel() {
		return this._m.label;
	}
	
	public void setDescr(String value) {
		this._m.descr = value;
	}
	public String getDescr() {
		return this._m.descr;
	}
}