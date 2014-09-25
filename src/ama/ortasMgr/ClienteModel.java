package ama.ortasMgr;

public class ClienteModel {
	public String id;
	public String codice;
	public String nome;
	public String note;
	
	public ClienteEntity getEntity() {
		ClienteEntity e = new ClienteEntity(this);
		return e;
	}
}