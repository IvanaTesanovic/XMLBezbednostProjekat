package xb.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.dto.SearchAktDTO;
import xb.manager.ObjectManager;
import xb.model.Zakon;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get() {
		ModelAndView m = new ModelAndView("home");
		m.addObject("searchAktDTO", new SearchAktDTO());
		return m;
	}
	
	@RequestMapping(method = RequestMethod.POST, params = "search")
	public ModelAndView searchAkt(@Valid SearchAktDTO searchAktDTO) {
		ModelAndView m = new ModelAndView("home");
		//moram da appendujem "ns1:" pre metapodatka po kojem se pretrazuje
		//tag (metapodatak) je naziv elementa, a parametar (sadrzaj) je ono sta trazim unuar tog elementa
		
		ObjectManager<Zakon> om = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		m.addObject("searchAktDTO", new SearchAktDTO());
		String tag = searchAktDTO.getMetapodatak();
		String param = searchAktDTO.getSadrzaj();
		
		HashMap<String, ArrayList<String>> akati = null;
		
		if(tag.equals(""))
			akati = om.searchColByParam(param, DatabaseConnection.AKT_COL_ID);
		else
			akati = om.searchColByTagAndParam(tag, param, DatabaseConnection.AKT_COL_ID);
		
		ArrayList<String> results = new ArrayList<>();
		
		  ArrayList<ArrayList<String>> lista = new ArrayList<>();
		     
		  Iterator it = akati.values().iterator(); //iterator koji prolazi kroz listu lista
		  
		  while(it.hasNext()) {
		   lista.add((ArrayList<String>) it.next());
		  }
		  
		  for(int i = 0; i < lista.size(); i++) {
		   ArrayList<String> jedan = lista.get(i);
		   for(int j = 0; j < jedan.size(); j++)
		    results.add(jedan.get(j));
		  }

		  m.addObject("size", results.size());
		  return m;
		  
	}
}
