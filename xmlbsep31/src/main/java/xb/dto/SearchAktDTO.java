package xb.dto;

public class SearchAktDTO {
	
	private String metapodatak;
	private String sadrzaj;
	
	public SearchAktDTO() {}

	public SearchAktDTO(String metapodatak, String sadrzaj) {
		super();
		this.metapodatak = metapodatak;
		this.sadrzaj = sadrzaj;
	}

	public String getMetapodatak() {
		return metapodatak;
	}

	public void setMetapodatak(String metapodatak) {
		this.metapodatak = metapodatak;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	
}
