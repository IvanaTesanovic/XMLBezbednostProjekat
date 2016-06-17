package xb.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.marklogic.client.document.DocumentDescriptor;

import xb.database.DatabaseConnection;
import xb.manager.ObjectManager;
import xb.model.Clan;
import xb.model.Korisnici;
import xb.model.TipKorisnik;
import xb.model.Zakon;
import xb.model.Zakon.Deo;
import xb.model.Zakon.Deo.Glava;
import xb.model.Zakon.Deo.Glava.Odeljak;
import xb.model.Zakon.Deo.Glava.Odeljak.Pododeljak;

/**
 * Kontroler koji obradjuje zahteve koji dolaze sa pocetne stranice.
 * Na pocetnoj strani za gradjanina postoji search, a sa ove strane korisnici sistema (odbornici i predsednici skupstine)
 * mogu da se redirektuju na stranicu za logovanje.
 * @author Ivana
 *
 */
@RestController
@RequestMapping("/first")
public class FirstController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get() {
		
//		ObjectManager<Korisnici> korisnici = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
//		
//		Korisnici korisnicii = (Korisnici)korisnici.readFromDB(DatabaseConnection.USERS_DOC_ID);
//		
//		TipKorisnik korisnik = new TipKorisnik();
//		korisnik.setEmail("test@test");
//		korisnik.setIme("jedno ime");
//		korisnik.setPrezime("test");
//		korisnik.setKorisnickoIme("test");
//		korisnik.setLozinka("korisnik");
//		korisnik.setUloga("test");
//		
//		korisnicii.getKorisnik().add(korisnik);
//		korisnici.generateKeyStore(korisnik);
//		korisnici.writeObjectToDB(korisnicii, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID, false);
		
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

		return new ModelAndView("first");

	}

}
