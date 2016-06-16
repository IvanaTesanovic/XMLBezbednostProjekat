package xb.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import xb.dto.LoginUserDTO;


@Component
public class UserDTOValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		if(arg0 != null) {
			LoginUserDTO user = (LoginUserDTO)arg0;
			
			if(user.getUsername().equals(null) || user.getUsername().equals(""))
				arg1.rejectValue("username", "error.username.null");
			
			if(user.getPassword().equals(null) || user.getPassword().equals(""))
				arg1.rejectValue("password", "error.password.null");
			
		}
		
	}

}
