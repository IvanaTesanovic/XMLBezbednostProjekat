package xb.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.manager.ObjectManager;
import xb.model.Amandman;
import xb.query.QueryGenerator;

@RestController
@RequestMapping("/spisakAmandmana")
public class SpisakAmandmanaController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSpisakAkata() {
		ModelAndView m = new ModelAndView("spisakAmandmana");
		
		ObjectManager<Amandman> objects = new ObjectManager<Amandman>(SpisakAmandmanaController.class.getClassLoader().getResource("Schemas/Amandman.xsd"));
		ArrayList<Amandman> spisakAman = objects.executeQuery(QueryGenerator.getFilesInColl(DatabaseConnection.AMD_COL_ID));
		
		m.addObject("spisakAman", spisakAman);
		return m;
	}
}
