package xb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import xb.manager.ObjectManager;
import xb.model.Zakon;

@RestController
@RequestMapping("/spisakAkata")
public class SpisakAkataController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Zakon> getSpisakAkata() {
		
		ObjectManager<Zakon> zakon = new ObjectManager<>(SpisakAkataController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		//List<Zakon> zakoni = 
		
		return null;
	}

}
