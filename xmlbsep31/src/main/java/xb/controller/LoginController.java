package xb.controller;

import javax.validation.Valid;

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
	public ModelAndView authenticateUser(@Valid LoginUserDTO loginUserDTO) {
		ModelAndView m = null;
		
		ObjectManager<Korisnici> objects = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
		Korisnici korisnici = (Korisnici)objects.readFromDB(DatabaseConnection.USERS_DOC_ID);
		
		for(TipKorisnik tk : korisnici.getKorisnik()) {
			if(tk.getKorisnickoIme().equals(loginUserDTO.getUsername()) && tk.getLozinka().equals(loginUserDTO.getPassword())) {
				m = new ModelAndView("redirect:home");
				m.addObject("loginUserDTO", new LoginUserDTO());
				return m;
			}
			else {
				m = new ModelAndView("login");
				m.addObject("error", "true");
				m.addObject("loginUserDTO", new LoginUserDTO());
				return m;
			}
		}
		return m;
	}

}
