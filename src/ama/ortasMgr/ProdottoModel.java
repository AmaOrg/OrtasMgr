package ama.ortasMgr;

public class ProdottoModel {
	public String id;
	public String codice;
	public String nome;
	public int giac_min;
	public boolean is_prod_int;
	
	public ProdottoEntity getEntity() {
		ProdottoEntity e = new ProdottoEntity(this);
		return e;
	}
}