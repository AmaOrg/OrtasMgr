package ama.ortasMgr;

public class TipoMovimentazioneMagEntity {
	private TipoMovimentazioneMagModel _m;
	
	public TipoMovimentazioneMagEntity(TipoMovimentazioneMagModel m) {
		this._m = m;
	}
	
	public TipoMovimentazioneMagModel getModel() {
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
	
	public void setIsEntrata(boolean value) {
		this._m.is_entrata = value;
	}
	public boolean getIsEntrata() {
		return this._m.is_entrata;
	}
	
	public void setIsModDispLotto(boolean value) {
		this._m.is_mod_disp_lotto = value;
	}
	public boolean getIsModDispLotto() {
		return this._m.is_mod_disp_lotto;
	}
	
	public void setIsCaricoIniz(boolean value) {
		this._m.is_carico_iniz = value;
	}
	public boolean getIsCaricoIniz() {
		return this._m.is_carico_iniz;
	}
}