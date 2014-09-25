package ama.ortasMgr;

import java.util.GregorianCalendar;

public class LottoModel {
	public String id;
	public String codice;
	public GregorianCalendar scadenza;
	public int qta;
	public ProdottoModel prodotto;
	
	public LottoEntity getEntity() {
		LottoEntity e = new LottoEntity(this);
		return e;
	}
}