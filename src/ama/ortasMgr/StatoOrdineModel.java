package ama.ortasMgr;

public class StatoOrdineModel {
	public String id;
	public String codice;
	public String label;
	public String descr;
	
	public StatoOrdineEntity getEntity() {
		StatoOrdineEntity e = new StatoOrdineEntity(this);
		return e;
	}
}