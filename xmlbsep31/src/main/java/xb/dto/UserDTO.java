package xb.dto;

import javax.validation.constraints.Pattern;

public class UserDTO {
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String firstName;
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String lastName;
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String address;
	
	@Pattern(regexp = "^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$")
	private String username;
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String password;
	
	@Pattern(regexp = "^(?=\\s*\\S).*$")
	private String password2;
	
	public UserDTO() {}
	
	public UserDTO(String firstName, String lastName, String address, String username, String password,
			String password2) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.username = username;
		this.password = password;
		this.password2 = password2;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
}
