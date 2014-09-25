package ama.ortasMgr;

public class StatoOrdineProdottoModel {
	public String id;
	public String codice;
	public String label;
	public String descr;
	
	public StatoOrdineProdottoEntity getEntity() {
		StatoOrdineProdottoEntity e = new StatoOrdineProdottoEntity(this);
		return e;
	}
}