package ama.ortasMgr;

public class ClienteEntity {
	private ClienteModel _m;
	
	public ClienteEntity(ClienteModel m) {
		this._m = m;
	}
	
	public ClienteModel getModel() {
		return this._m;
	}
	
	public void setId(String value) {
		this._m.id = value;
	}
	public String getId() {
		return this._m.id;
	}
	
	public void setNome(String value) {
		if(value == null)
		{
			// Errore
		}
		
		if(value.length > 50)
		{
			// Errore
		}
		
		this._m.nome = value;
	}
	
	public void setNote(String value) {
		this._m.note = value;
	}
	public String getNote() {
		return this._m.note;
	}
}


