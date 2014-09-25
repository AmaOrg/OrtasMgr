package ama.ortasMgr;

public class OrdineProdottoEntity {
	private OrdineProdottoModel _m;
	
	public OrdineProdottoEntity() {
		this._m = new OrdineProdottoModel();
	}
	
	public OrdineProdottoEntity(OrdineProdottoModel m) {
		this._m = m;
	}
	
	public OrdineProdottoModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setQta(int value) {
		this._m.qta = value;
	}
	public int getQta() {
		return this._m.qta;
	}
	
	public void setStatoOrdineProdotto(StatoOrdineProdottoEntity value) {
		this._m.stato_ord_prod = value.getModel();
	}
	public StatoOrdineProdottoEntity getStatoOrdineProdotto() {
		return this._m.stato_ord_prod.getEntity();
	}
	
	public void setOrdine(OrdineEntity value) {
		this._m.ordine = value.getModel();
	}
	public OrdineEntity getOrdine() {
		return this._m.ordine.getEntity();
	}
	
	public void setProdotto(ProdottoEntity value) {
		this._m.prodotto = value.getModel();
	}
	public ProdottoEntity getProdotto() {
		return this._m.prodotto.getEntity();
	}
}
