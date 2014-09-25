package ama.ortasMgr;

public class OrdineEntity {
	private OrdineModel _m;
	
	public OrdineEntity(OrdineModel m) {
		this._m = m;
	}
	
	public OrdineModel getModel() {
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
	
	public void setData(GregorianCalendar value) {
		this._m.data = value;
	}
	public GregorianCalendar getData() {
		return this._m.data;
	}
	
	public void setTipoOrdine(TipoOrdineEntity value) {
		this._m.tipo_ordine = value.getModel();
	}
	public TipoOrdineEntity getTipoOrdine() {
		return this._m.tipo_ordine.getEntity();
	}
	
	public void setStatoOrdine(StatoOrdineEntity value) {
		this._m.stato_ordine = value.getModel();
	}
	public StatoOrdineEntity getStatoOrdine() {
		return this._m.stato_ordine.getEntity();
	}
	/*
	public void setOrdineProdotto(List<OrdineProdottoEntity> value) {
		// TODO fare un ciclo su tutte le entity della list
			this._m.ord_prod = value.getModel();
	}
	public List<OrdineProdottoEntity> getOrdineProdotto() {
		return this._m.ord_prod;
	}
	*/
	
	public void setCliente(ClienteEntity value) {
		this._m.cliente = value.getModel();
	}
	public ClienteEntity getCliente() {
		return this._m.cliente.getEntity();
	}
}