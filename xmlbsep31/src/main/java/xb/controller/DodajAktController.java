package xb.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import xb.dto.PredlogDTO;
import xb.manager.ObjectManager;
import xb.model.Zakon;

/**
 * Kontroler koji obradjuje zahteve koji dolaze sa stranice za dodavanje akata,
 * kojoj pristup imaju ulogovani odbornici.
 */
@Controller
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
		if (zakon.validateAndSaveXMLAkt(predlogDTO.getText())) {
			m = new ModelAndView("redirect:home");
			m.addObject("predlogDTO", predlogDTO);
		} else {
			m = new ModelAndView("dodajAkt");
			m.addObject("error", "true");
			m.addObject("predlogDTO", predlogDTO);
		}
		return m;
	}
}
