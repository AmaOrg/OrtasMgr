package ama.ortasMgr;

public class MovimentazioneMagModel {
	public GregorianCalendar data;
	public String id;
	public int qta;
	public String note;
	public TipoMovimentazioneMagModel tipo_mov_mag;
	public MagazzinoModel mag;
	public LottoModel lotto;
	
	public MovimentazioneMagEntity getEntity() {
		MovimentazioneMagEntity e = new MovimentazioneMagEntity(this);
		return e;
	}
}