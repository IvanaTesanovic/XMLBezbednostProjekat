package xb.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import xb.dto.SignUpUserDTO;


@Component
public class SignUpUserDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if(arg0 != null) {
			SignUpUserDTO user = (SignUpUserDTO)arg0;
			
			if(user.getUsername().equals(null) || user.getUsername().equals(""))
				arg1.rejectValue("username", "error.username.null");
			
			if(user.getName().equals(null) || user.getName().equals(""))
				arg1.rejectValue("name", "error.name.null");
			
			if(user.getLastname().equals(null) || user.getLastname().equals(""))
				arg1.rejectValue("lastname", "error.lastname.null");
			
			if(user.getRole().equals(null) || user.getRole().equals(""))
				arg1.rejectValue("role", "error.role.null");
			
			if(user.getEmail().equals(null) || user.getEmail().equals(""))
				arg1.rejectValue("email", "error.email.null");
			
			if(user.getPassword().equals(null) || user.getPassword().equals(""))
				arg1.rejectValue("password", "error.password.null");
		}
	}
}
