package xb.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.manager.ObjectManager;
import xb.model.Korisnici;
import xb.model.TipKorisnik;

/**
 * Kontroler koji obradjuje zahteve koji dolaze sa pocetne stranice.
 * Na pocetnoj strani za gradjanina postoji search, a sa ove strane korisnici sistema (odbornici i predsednici skupstine)
 * mogu da se redirektuju na stranicu za logovanje.
 * @author Ivana
 *
 */
@Controller
@RequestMapping("/first")
public class FirstController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get() {
		
//		ObjectManager<Korisnici> korisnici = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
////		
//		Korisnici korisnicii = (Korisnici)korisnici.readFromDB(DatabaseConnection.USERS_DOC_ID);
////		
//		TipKorisnik korisnik = new TipKorisnik();
//		korisnik.setEmail("s.m@gmail.com");
//		korisnik.setIme("sonja");
//		korisnik.setPrezime("mijatovic");
//		korisnik.setKorisnickoIme("freshsonia");
//		korisnik.setLozinka(PasswordEncoder.getEncodedPassword("maslacak", "freshsonia"));
//		korisnik.setUloga("odbornik");
//		
//		korisnici.generateKeyStore(korisnik);
		
//		ObjectManager<Zakon> zakon = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
//		korisnicii.getListaKorisnika().add(korisnik);
//		
//		korisnici.writeObjectToDB(korisnicii, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID);
		
		
		
//		ObjectManager<Zakon> zakon = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
//		
//		Zakon zak = new Zakon();
//		Random rand = new Random();
//		String id = String.valueOf(rand.nextInt());
//		
//		Zakon.Deo deo = new Deo();
//		
//		Zakon.Deo.Glava glava = new Glava();
//		
//		Clan clan = new Clan();
//		clan.setID("id clana");
//		clan.setNaziv("naziv clana");
//		
//		//clan.getta
//		
//		Zakon.Deo.Glava.Odeljak.Pododeljak pododeljak = new Pododeljak();
//		pododeljak.setID("pododeljak id");
//		pododeljak.setNaziv("naziv pododeljka");
//		
//		pododeljak.getClan().add(clan);
//		
//		Zakon.Deo.Glava.Odeljak odeljak = new Odeljak();
//		odeljak.setNaziv("odeljak 1");
//		//odeljak.getPododeljak().add(pododeljak);
//		
//		glava.setID("smor");
//		glava.setNaziv("naziv");
//		
//		glava.getOdeljak().add(odeljak);
//		
//		deo.getGlava().add(glava);
//		
//		zak.setID(id);
//		zak.setNaziv("zakon" + id);
//		zak.getDeo().add(deo);
		
		//DocumentDescriptor desc = zakon.writeObjectToDB(zak, DatabaseConnection.AKT_COL_ID);
		//return desc.getUri();
		//boolean bla = zakon.verifySignature("9008714704406531439.xml");
//		boolean bla = zakon.decryptDocument("6219512914215934312.xml");
//		return String.valueOf(bla);
		
		//zakon.sendXMLtoIAGNS("5982308409546318229.xml");
		//return "das";

		return new ModelAndView("redirect:home");

	}

}
