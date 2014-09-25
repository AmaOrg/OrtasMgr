package ama.ortasMgr;

public class ProdottoEntity {
	private ProdottoModel _m;
	
	public ProdottoEntity() { 
		this._m = new ProdottoModel();
	}
	
	public ProdottoEntity(ProdottoModel m) {
		this._m = m;
	}
	
	public ProdottoModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setCodice(String value) {
		this._m.codice = value;
	}
	public String getCodice() {
		return this._m.codice;
	}
	
	public void setNome(String value) {
		this._m.nome = value;
	}
	public String getNome() {
		return this._m.nome;
	}
	
	public void setGiacMin(int value) {
		this._m.giac_min = value;
	}
	public int getGiacMin() {
		return this._m.giac_min;
	}
	
	public void setIsProdInt(boolean value) {
		this._m.is_prod_int = value;
	}
	public boolean getIsProdInt() {
		return this._m.is_prod_int;
	}
}
