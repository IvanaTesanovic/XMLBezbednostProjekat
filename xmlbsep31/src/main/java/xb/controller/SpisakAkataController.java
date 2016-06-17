package xb.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
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
}
