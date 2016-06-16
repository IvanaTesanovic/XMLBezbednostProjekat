package xb.dto;

public class PredlogDTO {
	
	private String text;
	
	public PredlogDTO() {}

	public PredlogDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
