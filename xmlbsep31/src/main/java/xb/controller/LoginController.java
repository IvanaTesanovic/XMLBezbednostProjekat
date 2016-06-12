package xb.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xb.database.DatabaseConnection;
import xb.dto.LoginUserDTO;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String logIn(ModelMap m) {
		m.addAttribute("loginUserDTO", new LoginUserDTO());
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String authenticateUser(@Valid LoginUserDTO loginUserDTO, ModelMap m) {
		String view = "";
		m.addAttribute("loginUserDTO", new LoginUserDTO());
		
		ObjectManager<Korisnici> objects = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
		Korisnici korisnici = (Korisnici)objects.readFromDB(DatabaseConnection.USERS_DOC_ID);
		
		for(TipKorisnik tk : korisnici.getKorisnik()) {
			if(tk.getKorisnickoIme().equals(loginUserDTO.getUsername()) && tk.getLozinka().equals(loginUserDTO.getPassword())) {
				view += "home";
				break;
			}
			else {
				m.addAttribute("error", "true");
				view += "login";
			}
		}

		return view;
	}

}
