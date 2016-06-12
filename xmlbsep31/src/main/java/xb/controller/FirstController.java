package xb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xb.database.DatabaseConnection;
import xb.database.Util;
import xb.manager.DatabaseManager;
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
	public String get() {
		
//		ObjectManager<Korisnici> korisnici = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Korisnici.xsd"));
//		
//		Korisnici korisnicii = new Korisnici();
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
//		
//		korisnici.writeObjectToDB(korisnicii, DatabaseConnection.USERS_DOC_ID, DatabaseConnection.USERS_COL_ID);
		
		return "first";
	}

}
