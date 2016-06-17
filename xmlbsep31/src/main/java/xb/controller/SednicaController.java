package xb.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.dto.SearchAktDTO;
import xb.dto.SednicaDTO;
import xb.manager.ObjectManager;
import xb.model.Zakon;
import xb.query.QueryGenerator;

@RestController
@RequestMapping("/sednica")
public class SednicaController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView sednica(){
		ModelAndView model = new ModelAndView("sednica");
		model.addObject("sednicaDTO", new SednicaDTO());
		return model;
	}
	
	@RequestMapping(value="/{zakonId}", method = RequestMethod.GET)
	public ModelAndView getRestaurant(@PathVariable String zakonId) {
		//ubaciti akt u kolekciju usvojenih i izbaciti iz kolekcije predlozenih
		ObjectManager<Zakon> zakoni = new ObjectManager<>(SpisakAkataController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		ArrayList<Zakon> predlozeni = zakoni.executeQuery(QueryGenerator.getFilesInColl(DatabaseConnection.AKT_COL_ID));
		Zakon zakon = null;
		
		for(int i = 0; i < predlozeni.size(); i++)
			if(predlozeni.get(i).getID().equals(zakonId)) {
				zakon = predlozeni.get(i);
				break;
			}
		
		String uri = zakoni.writeObjectToDB(zakon, DatabaseConnection.USV_AKT_COL_ID, false).getUri();
		ModelAndView m = new ModelAndView("home");
		m.addObject("searchAktDTO", new SearchAktDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(@Valid SednicaDTO sednicaDTO){
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
			model = new ModelAndView("sednica");
			model.addObject("sednicaDTO", sednicaDTO);
			return model;
		}
	}
	
}
