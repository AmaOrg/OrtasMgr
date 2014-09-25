package ama.ortasMgr;

public class TipoMovimentazioneMagModel {
	public String id;
	public String codice;
	public String label;
	public String descr;
	public boolean is_entrata;
	public boolean is_mod_disp_lotto;
	public boolean is_carico_iniz;
	
	public TipoMovimentazioneMagEntity getEntity() {
		TipoMovimentazioneMagEntity e = new TipoMovimentazioneMagEntity(this);
		return e;
	}
}