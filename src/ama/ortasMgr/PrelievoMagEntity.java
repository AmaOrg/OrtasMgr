package ama.ortasMgr;

public class PrelievoMagEntity {
	private PrelievoMagModel _m;
	
	public PrelievoMagEntity(PrelievoMagModel m) {
		this._m = m;
	}
	
	public PrelievoMagModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setOrdineProdotto(OrdineProdottoEntity value) {
		this._m.ordine_prodotto = value.getModel();
	}
	public OrdineProdottoEntity getOrdineProdotto() {
		return this._m.ordine_prodotto.getEntity();
	}
	
	public void setMovimentazioneMag(MovimentazioneMagEntity value) {
		this._m.movmag = value.getModel();
	}
	public MovimentazioneMagEntity getMovimentazioneMag() {
		return this._m.movmag.getEntity();
	}
	
	public void setSpedizione(SpedizioneEntity value) {
		this._m.spedizione = value.getModel();
	}
	public SpedizioneEntity getSpedizione() {
		return this._m.spedizione.getEntity();
	}
}