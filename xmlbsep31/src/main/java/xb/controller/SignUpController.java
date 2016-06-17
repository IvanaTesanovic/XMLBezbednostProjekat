package xb.controller;

import java.util.Calendar;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.dto.LoginUserDTO;
import xb.dto.SignUpUserDTO;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.password.PasswordEncoder;
import xb.validation.SignUpUserDTOValidator;

@RestController
@RequestMapping(value = "/signup")
public class SignUpController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView signUp() {
		ModelAndView m = new ModelAndView("signup");
		m.addObject("signUpUserDTO", new SignUpUserDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@Valid SignUpUserDTO signUpUserDTO, BindingResult bindingResult) {
		ModelAndView m = null;
		Korisnici korisnici;
		SignUpUserDTOValidator validator = new SignUpUserDTOValidator();
		validator.validate(signUpUserDTO, bindingResult);
		
		SignUpUserDTO user = new SignUpUserDTO(signUpUserDTO.getUsername(), signUpUserDTO.getName(), 
				signUpUserDTO.getLastname(), signUpUserDTO.getRole(), signUpUserDTO.getEmail(), signUpUserDTO.getPassword());
				
		if (bindingResult.hasErrors()) {
			m = new ModelAndView("signup");
			m.addObject("signUpUserDTO", signUpUserDTO);
			m.addObject("user", user);
		} else {
			ObjectManager<Korisnici> objects = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
			
			TipKorisnik tk = new TipKorisnik();
			tk.setKorisnickoIme(user.getUsername());
			tk.setIme(user.getName());
			tk.setPrezime(user.getLastname());
			tk.setUloga(user.getRole());
			tk.setLozinka(PasswordEncoder.getEncodedPassword(user.getPassword(), user.getUsername()));
			tk.setEmail(user.getEmail());
			
			if((korisnici = (Korisnici)objects.readFromDB(DatabaseConnection.USERS_DOC_ID)) == null) {
				objects.writeObjectToDB(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false);
			} else {
				korisnici.getListaKorisnika().add(tk);
				objects.writeObjectToDB(korisnici, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false);
			}
			
			m = new ModelAndView("redirect:login");
			m.addObject("signUpUserDTO", signUpUserDTO);
			//m.addObject("uloga", tk.getUloga());
			return m;
		}
		return m;
	}
	
	@RequestMapping(params = "cancel", method = RequestMethod.POST)
	public String cancel() {
		return "redirect:login";
	}	
}
