package ama.ortasMgr;

public class PrelievoMagModel {
	public String id;
	public OrdineProdottoModel ordine_prodotto;
	public MovimentazioneMagModel  movmag;
	public SpedizioneModel spedizione;
	
	public PrelievoMagEntity getEntity() {
		PrelievoMagEntity e = new PrelievoMagEntity(this);
		return e;
	}
}