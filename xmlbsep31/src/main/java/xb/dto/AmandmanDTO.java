package xb.dto;

public class AmandmanDTO {
	
	private String idAkta;
	private String putanjaOdredbe;
	private String predlozenoResenje;
	private String sadrzajResenja;
	
	public AmandmanDTO() {}
	
	public AmandmanDTO(String idAkta, String putanjaOdredbe, String predlozenoResenje, String sadrzajResenja) {
		super();
		this.idAkta = idAkta;
		this.putanjaOdredbe = putanjaOdredbe;
		this.predlozenoResenje = predlozenoResenje;
		this.sadrzajResenja = sadrzajResenja;
	}

	public String getIdAkta() {
		return idAkta;
	}

	public void setIdAkta(String idAkta) {
		this.idAkta = idAkta;
	}

	public String getPutanjaOdredbe() {
		return putanjaOdredbe;
	}

	public void setPutanjaOdredbe(String putanjaOdredbe) {
		this.putanjaOdredbe = putanjaOdredbe;
	}

	public String getPredlozenoResenje() {
		return predlozenoResenje;
	}

	public void setPredlozenoResenje(String predlozenoResenje) {
		this.predlozenoResenje = predlozenoResenje;
	}

	public String getSadrzajResenja() {
		return sadrzajResenja;
	}

	public void setSadrzajResenja(String sadrzajResenja) {
		this.sadrzajResenja = sadrzajResenja;
	}
	
}
