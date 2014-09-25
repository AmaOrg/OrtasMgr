package ama.ortasMgr;

public class TipoOrdineModel {
	public String id;
	public String codice;
	public String label;
	public String descr;
	
	public TipoOrdineEntity getEntity() {
		TipoOrdineEntity e = new TipoOrdineEntity(this);
		return e;
	}
}
