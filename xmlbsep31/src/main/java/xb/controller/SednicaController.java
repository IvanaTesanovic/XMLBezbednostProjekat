package xb.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.dto.SednicaDTO;

@RestController
@RequestMapping("/sednica")
public class SednicaController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sednica(){
		ModelAndView model = new ModelAndView("sednica");
		model.addObject("sednicaDTO", new SednicaDTO());
		return model;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit (@Valid SednicaDTO sednicaDTO){
		ModelAndView model = null;

		int za =Integer.parseInt(sednicaDTO.getZa());
		int protiv = Integer.parseInt(sednicaDTO.getProtiv());
		
		if (za > protiv) {
			System.out.println("za");
			model = new ModelAndView("sednica");
			model.addObject("sednicaDTO", sednicaDTO);
			return model;
		}
		else if (za < protiv) {
			System.out.println("protiv");
			model = new ModelAndView("sednica");
			model.addObject("sednicaDTO", sednicaDTO);
			return model;
		}
		else {
			System.out.println("Nesto se pojebalo!!!!!!!");
			model = new ModelAndView("sednica");
			model.addObject("sednicaDTO", sednicaDTO);
			return model;
		}
	} 
}
