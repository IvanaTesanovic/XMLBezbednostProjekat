package xb.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.dto.SearchAktDTO;
import xb.manager.ObjectManager;
import xb.model.Zakon;
import xb.query.QueryGenerator;

@RestController
@RequestMapping("/spisakAkata")
public class SpisakAkataController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSpisakAkata() {
		ModelAndView m = new ModelAndView("spisakAkata");
		
		ObjectManager<Zakon> objects = new ObjectManager<Zakon>(SpisakAkataController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		ArrayList<Zakon> spisakZakona = objects.executeQuery(QueryGenerator.getFilesInColl(DatabaseConnection.AKT_COL_ID));
		
		m.addObject("spisakZakona", spisakZakona);
		return m;
	}
	
	@RequestMapping(value="/{zakonId}", method = RequestMethod.GET)
	public ModelAndView getRestaurant(@PathVariable String zakonId) {
		//akt je odbijen, treba ga staviti u listu odbijenih
		ObjectManager<Zakon> zakoni = new ObjectManager<>(SpisakAkataController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		ArrayList<Zakon> predlozeni = zakoni.executeQuery(QueryGenerator.getFilesInColl(DatabaseConnection.AKT_COL_ID));
		Zakon zakon = null;
		
		//naci ovaj zakon i smestiti ga u listu
		for(int i = 0; i < predlozeni.size(); i++)
			if(predlozeni.get(i).getID().equals(zakonId)) {
				zakon = predlozeni.get(i);
				break;
			}
		
		String uri = zakoni.writeObjectToDB(zakon, DatabaseConnection.ODB_AKT_COL_ID, false).getUri();
		ModelAndView m = new ModelAndView("home");
		m.addObject("searchAktDTO", new SearchAktDTO());
		return m;
		
	}
	
}
