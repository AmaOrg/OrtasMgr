package ama.ortasMgr;

import java.util.GregorianCalendar;

public class OrdineModel {
	public String id;
	public String codice;
	public GregorianCalendar data;
	public TipoOrdineModel tipo_ordine;
	public StatoOrdineModel stato_ordine;
	public ClienteModel cliente;
	
	public OrdineEntity getEntity() {
		OrdineEntity e = new OrdineEntity(this);
		return e;
	}
}