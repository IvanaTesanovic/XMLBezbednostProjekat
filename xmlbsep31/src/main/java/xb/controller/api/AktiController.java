package xb.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/akti")
public class AktiController {
	
	@RequestMapping(method = RequestMethod.GET)
	public void get() {
		
	}

	@RequestMapping(value = "/dodaj", method = RequestMethod.GET)
	public void dodajAkt() {
		
	}
	
	
}
