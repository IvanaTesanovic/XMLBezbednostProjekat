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
import xb.dto.AmandmanDTO;
import xb.dto.LoginUserDTO;
import xb.dto.PredlogDTO;
import xb.manager.ObjectManager;
import xb.model.Amandman;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.model.Zakon;
import xb.model.Zakon.Deo;
import xb.model.Zakon.Deo.Glava;
import xb.validation.ReflectionUtils;
import xb.validation.XMLValidator;

/**
 * Kontroler koji obradjuje zahteve koji dolaze sa stranice za dodavanje amandmana,
 * kojoj pristup imaju ulogovani odbornici.
 */
@RestController
@RequestMapping("/dodajAmandman")
public class DodajAmandmanController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get() {
		ModelAndView m = new ModelAndView("dodajAmandman");
		m.addObject("amandmanDTO", new AmandmanDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String dodajAmandman(@Valid AmandmanDTO amandmanDTO, ModelAndView m) {
//		ModelAndView m;
//		
//		ObjectManager<Zakon> zakon = new ObjectManager<Zakon>(DodajAmandmanController.class.getClassLoader().getResource("Schemas/Amandman-novi.xsd"));
//		if (zakon.validateAndSaveXMLAmandman(predlogDTO.getText())) {
//			m = new ModelAndView("redirect:home");
//			m.addObject("predlogDTO", predlogDTO);
//		} else {
//			m = new ModelAndView("dodajAmandman");
//			m.addObject("error", "true");
//			m.addObject("predlogDTO", predlogDTO);
//		}
//		return m;
		
		//ModelAndView m = new ModelAndView("dodajAmandman");
		m.addObject("amandmanDTO", amandmanDTO);
		
		String idAkta = amandmanDTO.getIdAkta();
		String putanja = amandmanDTO.getPutanjaOdredbe();
		String sadrzaj = amandmanDTO.getSadrzajResenja();
		String predlozeno = amandmanDTO.getPredlozenoResenje(); //Dodavanje, Brisanje, Izmena
		
		ObjectManager<Amandman> amandman = new ObjectManager<Amandman>(DodajAmandmanController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		//return amandman.partialUpdate(idAkta, sadrzaj, predlozeno);
		
		ObjectManager<Zakon> zakon = new ObjectManager<>(DodajAmandmanController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		return zakon.updateAkt(idAkta, putanja, predlozeno, sadrzaj);
		
	}
}
