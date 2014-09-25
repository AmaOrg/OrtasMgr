package ama.ortasMgr;

public class MovimentazioneMagEntity {
	private MovimentazioneMagModel _m;
	
	public MovimentazioneMagEntity(MovimentazioneMagModel m) {
		this._m = m;
	}
	
	public MovimentazioneMagModel getModel() {
		return this._m;
	}
	
	public void setData(GregorianCalendar value) {
		this._m.data = value;
	}
	public GregorianCalendar getData() {
		return this._m.data;
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
	
	public void setNote(String value) {
		this._m.note = value;
	}
	public String getNote() {
		return this._m.note;
	}
	
	public void setTipoMovimentazioneMag(TipoMovimentazioneMagEntity value) {
		this._m.tipo_mov_mag = value.getModel();
	}
	public TipoMovimentazioneMagEntity getTipoMovimentazioneMag() {
		return this._m.tipo_mov_mag.getEntity();
	}
	
	public void setMagazzino(MagazzinoEntity value) {
		this._m.mag = value.getModel();
	}
	public MagazzinoEntity getMagazzino() {
		return this._m.mag.getEntity();
	}
	
	public void setLotto(LottoEntity value) {
		this._m.lotto = value.getModel();
	}
	public LottoEntity getLotto() {
		return this._m.lotto.getEntity();
	}
}