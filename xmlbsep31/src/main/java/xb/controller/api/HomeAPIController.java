package xb.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeAPIController {
	
	public ModelAndView getRoot() {
		return new ModelAndView("login");
	}

}
