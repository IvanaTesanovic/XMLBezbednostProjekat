package xb.dto;

public class SednicaDTO {

	private String za;
	private String protiv;
	private String suzdrzani;
	
	public SednicaDTO() {
		super();
	}
	public SednicaDTO(String za, String protiv, String suzdrzani) {
		super();
		this.za = za;
		this.protiv = protiv;
		this.suzdrzani = suzdrzani;
	}
	public String getZa() {
		return za;
	}
	public void setZa(String za) {
		this.za = za;
	}
	public String getProtiv() {
		return protiv;
	}
	public void setProtiv(String protiv) {
		this.protiv = protiv;
	}
	public String getSuzdrzani() {
		return suzdrzani;
	}
	public void setSuzdrzani(String suzdrzani) {
		this.suzdrzani = suzdrzani;
	}	
}
