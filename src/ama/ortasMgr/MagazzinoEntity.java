package ama.ortasMgr;

public class MagazzinoEntity {
	private MagazzinoModel _m;
	
	public MagazzinoEntity(MagazzinoModel m) {
		this._m = m;
	}
	
	public MagazzinoModel getModel() {
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
}