package ama.ortasMgr;

public class OrdineProdottoModel {
	public String id;
	public int qta;
	public StatoOrdineProdottoModel stato_ord_prod;
	public OrdineModel ordine;
	public ProdottoModel prodotto;
	
	public OrdineProdottoEntity getEntity() {
		OrdineProdottoEntity e = new OrdineProdottoEntity(this);
		return e;
	}
}