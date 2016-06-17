package xb.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.dto.LoginUserDTO;
import xb.dto.SearchAktDTO;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.validation.UserDTOValidator;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView logIn() {
		ModelAndView m = new ModelAndView("login");
		m.addObject("loginUserDTO", new LoginUserDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView authenticateUser(@Valid LoginUserDTO loginUserDTO, BindingResult bindingResult) {
		ModelAndView m = null;
		UserDTOValidator validator = new UserDTOValidator();
		validator.validate(loginUserDTO, bindingResult);
		
		if(bindingResult.hasErrors()) {
			m = new ModelAndView("login");
			m.addObject("loginUserDTO", loginUserDTO);
		}
		else {
			ObjectManager<Korisnici> objects = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
			Korisnici korisnici = (Korisnici)objects.readFromDB(DatabaseConnection.USERS_DOC_ID);
			
			for(TipKorisnik tk : korisnici.getKorisnik()) {
				if(tk.getKorisnickoIme().equals(loginUserDTO.getUsername()) && tk.getLozinka().equals(loginUserDTO.getPassword())) {
					//if(tk.getUloga().equals("test")) {
						m = new ModelAndView("redirect:home");
						m.addObject("loginUserDTO", loginUserDTO);
						m.addObject("uloga", tk.getUloga());
						return m;
					//}
				}
				else {
					m = new ModelAndView("login");
					m.addObject("error", "true");
					m.addObject("loginUserDTO", loginUserDTO);
					return m;
				}
			}
		}
		return m;
	}

}
