package xb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spisakAkata")
public class SpisakAkataController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getSpisakAkata() {
		return "spisakAkata";
	}

}
