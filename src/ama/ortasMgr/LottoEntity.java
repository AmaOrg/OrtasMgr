package ama.ortasMgr;

public class LottoEntity {
	private LottoModel _m;
	
	public LottoEntity(LottoModel m) {
		this._m = m;
	}
	
	public LottoModel getModel() {
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
	
	public void setScadenza(GregorianCalendar value) {
		this._m.scadenza = value;
	}
	public GregorianCalendar getScadenza() {
		return this._m.scadenza;
	}
	
	public void setQta(int value) {
		this._m.qta = value;
	}
	public int getQta() {
		return this._m.qta;
	}
	
	public void setProdotto(ProdottoModel value) {
		this._m.prodotto = value;
	}
	public ProdottoEntity getProdotto() {
		return this._m.prodotto.getEntity();
	}
}