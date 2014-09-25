package ama.ortasMgr;

public class MagazzinoModel {
	public String id;
	public String codice;
	public String nome;
	
	public MagazzinoEntity getEntity() {
		MagazzinoEntity e = new MagazzinoEntity(this);
		return e;
	}
}