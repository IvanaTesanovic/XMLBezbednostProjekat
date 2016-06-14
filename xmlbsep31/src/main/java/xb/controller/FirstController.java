package xb.controller;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xb.database.DatabaseConnection;
import xb.database.Util;
import xb.manager.DatabaseManager;
import xb.manager.ObjectManager;
import xb.model.Zakon;
import xb.signing.SignEnveloped;

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
		
		ObjectManager<Zakon> zakon = new ObjectManager<>(FirstController.class.getClassLoader().getResource("Schemas/Akt.xsd"));
		
		Zakon zak = new Zakon();
		Random rand = new Random();
		String id = String.valueOf(rand.nextInt());
		
		zak.setID(id);
		zak.setNaziv("zakon" + id);
		
		//zakon.writeObjectToDB(zak, DatabaseConnection.AKT_COL_ID);
		
		SignEnveloped sign = new SignEnveloped();
		sign.sign();
		
		//return new ModelAndView("first");
		return FirstController.class.getClassLoader().getResource("cfg").toString();
	}

}
