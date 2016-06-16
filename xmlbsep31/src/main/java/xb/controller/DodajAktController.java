package xb.controller;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.validation.Valid;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;

import xb.database.DatabaseConnection;
import xb.dto.LoginUserDTO;
import xb.dto.PredlogDTO;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.model.Zakon;
import xb.model.Zakon.Deo;
import xb.model.Zakon.Deo.Glava;
import xb.validation.ReflectionUtils;
import xb.validation.XMLValidator;

/**
 * Kontroler koji obradjuje zahteve koji dolaze sa stranice za dodavanje akata,
 * kojoj pristup imaju ulogovani odbornici.
 */
@RestController
@RequestMapping("/dodajAkt")
public class DodajAktController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView dodajAkt() {
		ModelAndView m = new ModelAndView("dodajAkt");
		m.addObject("predlogDTO", new PredlogDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView authenticateUser(@Valid PredlogDTO predlogDTO) {
		ModelAndView m;
		
		ObjectManager<Zakon> zakon = new ObjectManager<Zakon>(DodajAktController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		zakon.validateAndSaveXMLAkt(predlogDTO.getText());	
		
		m = new ModelAndView("home");
		m.addObject("predlogDTO", predlogDTO);
		return m;
	}
}
